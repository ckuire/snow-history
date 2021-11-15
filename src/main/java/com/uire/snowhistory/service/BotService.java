package com.uire.snowhistory.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.uire.snowhistory.common.Result;
import com.uire.snowhistory.entity.Count;
import com.uire.snowhistory.entity.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author uire
 * @date 2021/11/05 18:21
 **/
@Service
@RequiredArgsConstructor
public class BotService {

    private final CountService countService;

    public Result index(String metadataStr) {
        if (null != JSONUtil.parseObj(metadataStr).get("meta_event_type")
                && "heartbeat".equals(JSONUtil.parseObj(metadataStr).get("meta_event_type"))) {
            return null;
        }

        Metadata metadata = JSONUtil.toBean(metadataStr, Metadata.class);

        if (!metadata.getGroup_id().equals(196192098)) {
            return null;
        }

        // 先实现一个简单的查询统计
        if ("/count".equals(metadata.getMessage())) {
            Count count = countService.lambdaQuery()
                    .eq(Count::getUserId, metadata.getUser_id())
                    .eq(Count::getGroupId, metadata.getGroup_id()).one();
            String msgTemplate = "总消息数:{} \n发送的图片个数:{} \n发送带图片的消息个数:{} \n发送emoji个数:{} \n发送带emoji的消息个数:{} \n艾特次数:{} \n被艾特次数:{} \n撤回次数:{} \n被撤回次数:{} \n发送语音次数:{} \n分享音乐次数:{} \n分享链接次数:{} \n";
            return Result.builder()
                    .reply(StrUtil.format(msgTemplate, count.getChatCount(), count.getImageCount(), count.getHasImageCount(), count.getEmojiCount(), count.getHasEmojiCount(), count.getAtCount(), count.getBeAtCount(), count.getWithdrawCount(), count.getBeWithdrawCount(), count.getVoiceCount(), count.getMusicCount(), count.getShareCount()))
                    .build();
        }
        return null;
    }
}
