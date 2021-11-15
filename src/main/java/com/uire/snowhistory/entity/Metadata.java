package com.uire.snowhistory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author uire
 * @date 2021/11/04 16:49
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("metadata")
public class Metadata {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 事件发生的时间戳
    @TableField("time")
    private String time;
    // 收到事件的机器人 QQ 号
    @TableField("self_id")
    private String self_id;
    // 操作人id
    @TableField("operator_id")
    private String operator_id;
    // 	上报类型
    @TableField("post_type")
    private String post_type;
    // 通知类型
    @TableField("notice_type")
    private String notice_type;
    // 	消息类型
    @TableField("message_type")
    private String message_type;
    // 消息子类型, 正常消息是 normal, 匿名消息是 anonymous, 系统提示 ( 如「管理员已禁止群内匿名聊天」 ) 是 notice
    @TableField("sub_type")
    private String sub_type;
    // 消息 ID
    @TableField("message_id")
    private String message_id;
    // 群号
    @TableField("group_id")
    private String group_id;
    // 	发送者 QQ 号
    @TableField("user_id")
    private String user_id;
    // 	消息内容
    @TableField("message")
    private String message;
    // 	原始消息内容
    @TableField("raw_message")
    private String raw_message;
    // 	字体
    @TableField("font")
    private String font;
    // 	发送人信息
    @TableField(value = "sender", typeHandler = JacksonTypeHandler.class)
    private Sender sender;

    @TableField("meta")
    private String meta;

    @Data
    static class Sender {
        // 发送者 QQ 号
        private String user_id;
        // 	昵称
        private String nickname;
        // 	群名片／备注
        private String card;
        // 性别, male 或 female 或 unknown
        private String sex;
        // 年龄
        private Integer age;
        // 	地区
        private String area;
        // 成员等级
        private String level;
        // 角色, owner 或 admin 或 member
        private String role;
        // 专属头衔
        private String title;
    }

}
