package com.uire.snowhistory.service.command;

import com.uire.snowhistory.common.Result;

/**
 * @author uire
 * @date 2021/11/16 15:06
 **/
public interface ICommand {

    Result reply(String message, String userId, String groupId);

    boolean isOk(String message);
}
