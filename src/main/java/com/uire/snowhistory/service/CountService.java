package com.uire.snowhistory.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uire.snowhistory.entity.Count;
import com.uire.snowhistory.entity.enums.CountTypeEnums;
import com.uire.snowhistory.mapper.CountMapper;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author uire
 * @date 2021/11/05 10:13
 **/
@Service
public class CountService extends ServiceImpl<CountMapper, Count> {


    public void checkInit(String userId, String groupId) {
        if (lambdaQuery().eq(Count::getUserId, userId).eq(Count::getGroupId, groupId).count() == 0) {
            save(Count.builder().userId(userId).groupId(groupId).build());
        }
    }


    public void chatIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.CHAT_COUNT);
    }

    public void imageIncr(String userId, String groupId, Integer count) {
        incr(userId, groupId, CountTypeEnums.IMAGE_COUNT, count);
    }

    public void hasImageIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.HAS_IMAGE_COUNT);
    }

    public void emojiIncr(String userId, String groupId, Integer count) {
        incr(userId, groupId, CountTypeEnums.EMOJI_COUNT, count);
    }

    public void hasEmojiIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.HAS_EMOJI_COUNT);
    }

    public void atIncr(String userId, String groupId, Integer count) {
        incr(userId, groupId, CountTypeEnums.AT_COUNT, count);
    }

    public void beAtIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.BE_AT_COUNT);
    }

    public void voiceIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.VOICE_COUNT);
    }

    public void shareIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.SHARE_COUNT);
    }

    public void musicIncr(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.MUSIC_COUNT);
    }

    public void beWithdrawCount(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.BE_WITHDRAW_COUNT);
    }

    public void withdrawCount(String userId, String groupId) {
        incr(userId, groupId, CountTypeEnums.WITHDRAW_COUNT);
    }


    public void incr(String userId, String groupId, CountTypeEnums typeEnums) {
        incr(userId, groupId, typeEnums, 1);
    }

    public void incr(String userId, String groupId, CountTypeEnums typeEnums, Integer count) {
        String type = typeEnums.name().toLowerCase(Locale.ROOT);
        lambdaUpdate()
                .eq(Count::getUserId, userId)
                .eq(Count::getGroupId, groupId)
                .setSql(StrUtil.format("{} = {} + {}", type, type, count)).update();
    }
}
