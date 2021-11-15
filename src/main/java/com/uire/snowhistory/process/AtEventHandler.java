package com.uire.snowhistory.process;

import com.uire.snowhistory.entity.Metadata;

/**
 * @author uire
 * @date 2021/11/04 16:44
 **/
public class AtEventHandler implements EventHandler{
    @Override
    public boolean isMatch() {
        return false;
    }

    @Override
    public void go(Metadata metadata) {

    }
}
