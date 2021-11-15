package com.uire.snowhistory.entity.dto;

import lombok.Data;

/**
 * @author uire
 * @date 2021/11/03 15:19
 **/
@Data
public class RecordDTO {

    private Sender sender;
    private String group_id;
    private String message;
    private Long time;
    private Integer message_id;

    @Data
    public static class Sender{
        private String user_id;
        private String nickname;
        private String card;
        private String role;
        private String title;
        private String level;
    }

}
