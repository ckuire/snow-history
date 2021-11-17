package com.uire.snowhistory.service.command;

import cn.hutool.json.JSONUtil;
import com.uire.snowhistory.common.Result;
import com.uire.snowhistory.entity.Metadata;
import com.uire.snowhistory.service.CountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uire
 * @date 2021/11/05 18:21
 **/
@Service
@RequiredArgsConstructor
public class CommandService {

    private final CountService countService;
    private final ObjectProvider<List<ICommand>> commands;

    public Result index(String metadataStr) {
        if (null != JSONUtil.parseObj(metadataStr).get("meta_event_type")
                && "heartbeat".equals(JSONUtil.parseObj(metadataStr).get("meta_event_type"))) {
            return null;
        }

        Metadata metadata = JSONUtil.toBean(metadataStr, Metadata.class);

        // 临时屏蔽一下
//        if (!metadata.getGroup_id().equals(196192098)) {
//            return null;
//        }

        return commands.getIfAvailable(ArrayList::new)
                .stream().filter(command -> command.isOk(metadata.getMessage()))
                .map(command -> command.reply(metadata.getMessage(), metadata.getUser_id(), metadata.getGroup_id()))
                .findFirst().orElse(null);
    }

    public Result test(String message, String userId, String groupId){
        return commands.getIfAvailable(ArrayList::new)
                .stream().filter(command -> command.isOk(message))
                .map(command -> command.reply(message, userId, groupId))
                .findFirst().orElse(null);
    }
}
