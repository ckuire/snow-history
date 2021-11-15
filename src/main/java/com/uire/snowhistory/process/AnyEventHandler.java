package com.uire.snowhistory.process;

/**
 * 谁都会分发的处理器
 * @author uire
 * @date 2021/11/05 09:46
 **/
public abstract class AnyEventHandler implements EventHandler {

    @Override
    public boolean isMatch() {
        return true;
    }
}
