package com.uire.snowhistory.process;

import com.uire.snowhistory.entity.Metadata;

/**
 * 事件处理
 *
 * @author uire
 * @date 2021/11/04 16:40
 **/
public interface EventHandler {

    /**
     * 是否匹配
     */
    boolean isMatch();


    /**
     * 让我处理
     */
    void go(Metadata metadata);
}
