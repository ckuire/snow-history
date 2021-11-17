package com.uire.snowhistory.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uire.snowhistory.entity.Count;
import com.uire.snowhistory.entity.DetailCount;
import com.uire.snowhistory.entity.enums.CountTypeEnums;
import com.uire.snowhistory.mapper.CountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * @author uire
 * @date 2021/11/05 10:13
 **/
@Service
@RequiredArgsConstructor
public class CountService extends ServiceImpl<CountMapper, Count> {
    private final CountMapper countMapper;

    @Resource
    private MetadataService metadataService;

    public String getTop(String groupId) {
        List<Count> list = lambdaQuery().eq(Count::getGroupId, groupId)
                .orderByDesc(Count::getChatCount).last(" limit 3").list();

        String msgTemplate = "" +
                "\n {} 总发送消息数:{}" +
                "\n 发送了{}个图片" +
                "\n 发送了{}个emoji";

        StringBuffer reply = new StringBuffer();
        list.stream().forEach(count -> {
            String card = metadataService.getCard(count.getUserId(), groupId);
            reply.append(StrUtil.format(
                    msgTemplate, card, count.getChatCount(), count.getImageCount(), count.getEmojiCount()
            ));
            reply.append("\n");
        });
        return reply.toString();
    }

    public String getCount(String userId, String groupId) {
        Count count = lambdaQuery()
                .eq(Count::getUserId, userId)
                .eq(Count::getGroupId, groupId).one();
        String msgTemplate = "总消息数:{} \n发送的图片个数:{} \n发送带图片的消息个数:{} \n发送emoji个数:{} \n发送带emoji的消息个数:{} \n艾特次数:{} \n被艾特次数:{} \n撤回次数:{} \n被撤回次数:{} \n发送语音次数:{} \n分享音乐次数:{} \n分享链接次数:{} \n";
        return StrUtil.format(msgTemplate, count.getChatCount(),
                count.getImageCount(), count.getHasImageCount(),
                count.getEmojiCount(), count.getHasEmojiCount(),
                count.getAtCount(), count.getBeAtCount(),
                count.getWithdrawCount(), count.getBeWithdrawCount(),
                count.getVoiceCount(), count.getMusicCount(),
                count.getShareCount());
    }

    public String getDetail(String userId, String groupId, Integer current) {
        List<DetailCount> detail = countMapper.getDetail(userId, groupId);
        String template = "\n" +
                "在 {} 发送了 {} 条消息\n";

        StringBuffer result = new StringBuffer();
        int size = detail.size() > 10 ? 10 : detail.size();
        for (int i = 0; i < size; i++) {
            DetailCount detailCount = detail.get(i);
            result.append(StrUtil.format(template, detailCount.getDate(), detailCount.getCount()));
        }
        return result.toString();
    }

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
