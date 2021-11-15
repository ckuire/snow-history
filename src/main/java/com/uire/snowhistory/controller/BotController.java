package com.uire.snowhistory.controller;

import com.uire.snowhistory.common.Result;
import com.uire.snowhistory.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可交互机器人🤖
 * @author uire
 * @date 2021/11/05 18:16
 **/
@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping
    public Result post(@RequestBody String metadata){
        return botService.index(metadata);
    }
}
