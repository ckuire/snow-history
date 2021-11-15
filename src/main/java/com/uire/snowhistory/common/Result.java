package com.uire.snowhistory.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author uire
 * @date 2021/11/05 18:26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private String reply;
    private Boolean auto_escape;
    @Builder.Default
    private Boolean at_sender = true;
    private Boolean delete;
    private Boolean kick;
    private Boolean ban;
    private Integer ban_duration;
}
