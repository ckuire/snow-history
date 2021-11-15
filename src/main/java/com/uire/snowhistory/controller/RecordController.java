package com.uire.snowhistory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uire.snowhistory.entity.Record;
import com.uire.snowhistory.entity.dto.RecordDTO;
import com.uire.snowhistory.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author uire
 * @date 2021/11/03 13:46
 **/
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @PostMapping
    public void post(@RequestBody RecordDTO record) {
        if (null == record.getSender()) {
            return;
        }
        recordService.save(record);
    }

    @GetMapping("/page")
    public Page<Record> page(Integer current, Integer size) {
        return recordService.lambdaQuery().orderByDesc(Record::getCreateTime).page(new Page(current, size));
    }

}
