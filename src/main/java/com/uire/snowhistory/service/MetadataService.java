package com.uire.snowhistory.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uire.snowhistory.entity.Metadata;
import com.uire.snowhistory.mapper.MetadataMapper;
import com.uire.snowhistory.process.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uire
 * @date 2021/11/04 17:04
 **/
@Service
@RequiredArgsConstructor
public class MetadataService extends ServiceImpl<MetadataMapper, Metadata> {

    private final ObjectProvider<List<EventHandler>> eventHandlers;

    @Resource
    private CountService countService;

    public void index(String metadata) {
        System.out.println(metadata);

        if (null != JSONUtil.parseObj(metadata).get("meta_event_type")
                && "heartbeat".equals(JSONUtil.parseObj(metadata).get("meta_event_type"))) {
            return;
        }

        save(
                JSONUtil.toBean(metadata, Metadata.class)
                        .setMeta(metadata)
        );

        dispatcherHandler(JSONUtil.toBean(metadata, Metadata.class));
    }

    @Async
    public void dispatcherHandler(Metadata metadata) {
        countService.checkInit(metadata.getUser_id(), metadata.getGroup_id());

        eventHandlers.getIfAvailable(ArrayList::new)
                .stream().filter(EventHandler::isMatch)
                .forEach(eventHandler -> eventHandler.go(metadata));
    }

    /**
     * 一个瞎几把操作的获取用户群名称的方法，后续可能要加个用户管理了
     *
     * @param userId
     * @param groupId
     * @return
     */
    public String getCard(String userId, String groupId) {
        return lambdaQuery().eq(Metadata::getUser_id, userId).eq(Metadata::getGroup_id, groupId).orderByDesc(Metadata::getTime)
                .list().stream().findFirst().map(metadata -> {
                    Metadata.Sender sender = metadata.getSender();
                    if(StrUtil.isNotBlank(sender.getCard())){
                        return sender.getCard();
                    }
                    return sender.getNickname();
                }).orElse(null);
    }
}
