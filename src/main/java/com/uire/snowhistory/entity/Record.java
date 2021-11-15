package com.uire.snowhistory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author uire
 * @date 2021/11/03 13:41
 **/
@TableName("record")
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private String userId;
    @TableField("nickname")
    private String nickname;
    @TableField("message")
    private String message;
    @TableField("group_id")
    private String groupId;
    @TableField("card")
    private String card;
    @TableField("level")
    private String level;
    @TableField("role")
    private String role;
    @TableField("title")
    private String title;
    @TableField("time")
    private Long time;
    @TableField("message_id")
    private Integer messageId;
    @TableField("create_time")
    private Date createTime;

}
