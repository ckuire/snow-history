package com.uire.snowhistory;

import com.uire.snowhistory.common.Result;
import com.uire.snowhistory.service.command.CommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author uire
 * @date 2021/11/16 19:35
 **/
@SpringBootTest(classes = SnowHistoryApplication.class)
public class ReplyTest {

    @Autowired
    private CommandService commandService;

    @Test
    public void test() {
        Result test = commandService.test("/count detail", "823623735", "196192098");
        System.out.println(test);
    }
}
