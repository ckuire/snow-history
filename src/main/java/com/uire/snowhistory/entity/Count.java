package com.uire.snowhistory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author uire
 * @date 2021/11/05 09:49
 **/
@TableName("count")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Count {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private String userId;
    @TableField("group_id")
    private String groupId;
    /**
     * 聊天总次数(发送消息次数)
     */
    @TableField("chat_count")
    private Integer chatCount;
    /**
     * 发送的图片个数
     */
    @TableField("image_count")
    private Integer imageCount;
    /**
     * 含图片的聊天次数
     */
    @TableField("has_image_count")
    private Integer hasImageCount;
    /**
     * 发送的emoji个数
     */
    @TableField("emoji_count")
    private Integer emojiCount;
    /**
     * 含emoji的聊天次数
     */
    @TableField("has_emoji_count")
    private Integer hasEmojiCount;
    /**
     * 艾特次数
     */
    @TableField("at_count")
    private Integer atCount;
    /**
     * 被艾特次数
     */
    @TableField("be_at_count")
    private Integer beAtCount;
    /**
     * 发送语音次数
     */
    @TableField("voice_count")
    private Integer voiceCount;
    /**
     * 链接分享次数
     */
    @TableField("share_count")
    private Integer shareCount;
    /**
     * 音乐分享次数
     */
    @TableField("music_count")
    private Integer musicCount;

    /**
     * 撤回次数
     */
    @TableField("withdraw_count")
    private Integer withdrawCount;

    /**
     * 被撤回次数
     */
    @TableField("be_withdraw_count")
    private Integer beWithdrawCount;

}
