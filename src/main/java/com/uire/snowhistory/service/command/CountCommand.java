package com.uire.snowhistory.service.command;

import cn.hutool.core.util.ReUtil;
import com.uire.snowhistory.common.Result;
import com.uire.snowhistory.service.CountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uire
 * @date 2021/11/16 15:09
 **/
@Service
@RequiredArgsConstructor
public class CountCommand implements ICommand {

    static String topic = "/count";
    static List<String> sub = new ArrayList<String>() {{
        add("top");
        add("detail");
    }};
    static String regex = "/count[ ]?(top|detail)?$";
    private final CountService countService;

    public static void main(String[] args) {
        ReUtil.get(
                "/count ([top|detail])",
                "/count top",
                1
        );
    }

    @Override
    public Result reply(String message, String userId, String groupId) {
        String command = ReUtil.get(regex, message, 1);

        if (null == command) {
            return Result.builder()
                    .reply(countService.getCount(userId, groupId))
                    .build();
        } else if (command.equals("detail")) {
            return Result.builder()
                    .reply(countService.getDetail(userId, groupId, null))
                    .build();
        } else if (command.equals("top")) {
            return Result.builder()
                    .reply(countService.getTop(groupId))
                    .at_sender(false)
                    .build();
        }
        return null;
    }

    @Override
    public boolean isOk(String message) {
        return ReUtil.isMatch(regex, message);
    }
}
