package com.uire.snowhistory.process;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.uire.snowhistory.constant.RegexConstant;
import com.uire.snowhistory.entity.Metadata;
import com.uire.snowhistory.service.CountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 次数统计处理器
 *
 * @author uire
 * @date 2021/11/05 09:45
 **/
@Service
@RequiredArgsConstructor
public class CountEventEventHandler extends AnyEventHandler {
    private final CountService countService;

    @Override
    public void go(Metadata metadata) {
        countService.chatIncr(metadata.getUser_id(), metadata.getGroup_id());

        dispatcher(metadata);
    }

    public void dispatcher(Metadata metadata) {
        String message = metadata.getMessage();
        String userId = metadata.getUser_id();
        String group_id = metadata.getGroup_id();

        if (ReUtil.count(RegexConstant.REGEX_IMAGE, message) != 0) {
            countService.hasImageIncr(userId, group_id);
            countService.imageIncr(userId, group_id, ReUtil.count(RegexConstant.REGEX_IMAGE, message));
        }

        if (EmojiUtil.containsEmoji(message)) {
            countService.emojiIncr(userId, group_id, EmojiUtil.extractEmojis(message).size());
            countService.hasEmojiIncr(userId, group_id);
        }

        if (ReUtil.count(RegexConstant.REGEX_AT, message) != 0) {
            List<String> atAll = ReUtil.findAll(RegexConstant.REGEX_AT, message, 1);
            // 记录被艾特次数
            atAll.forEach(at -> countService.beAtIncr(Convert.toStr(at), group_id));

            // 艾特别人多少次
            countService.atIncr(userId, group_id, atAll.size());
        }

        if (ReUtil.isMatch(RegexConstant.REGEX_VOICE, message)) {
            countService.voiceIncr(userId, group_id);
        }

        if (ReUtil.count(RegexConstant.REGEX_MUSIC, message) != 0) {
            countService.musicIncr(userId, group_id);
        }

        if (ReUtil.count(RegexConstant.REGEX_SHARE, message) != 0) {
            countService.shareIncr(userId, group_id);
        }
        // 群消息撤回
        if ("group_recall".equals(metadata.getNotice_type())) {
            // 相等就是自己撤回的
            if (metadata.getOperator_id().equals(userId)) {
                countService.withdrawCount(userId, group_id);
            } else {
                countService.beWithdrawCount(userId, group_id);
            }
        }

    }
}
