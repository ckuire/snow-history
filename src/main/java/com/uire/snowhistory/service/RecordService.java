package com.uire.snowhistory.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uire.snowhistory.entity.Record;
import com.uire.snowhistory.entity.dto.RecordDTO;
import com.uire.snowhistory.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author uire
 * @date 2021/11/03 13:47
 **/
@Service
public class RecordService extends ServiceImpl<RecordMapper, Record> {

    public void save(RecordDTO dto) {
        save(
                Record.builder()
                        .userId(dto.getSender().getUser_id())
                        .message(dto.getMessage())
                        .card(dto.getSender().getCard())
                        .groupId(dto.getGroup_id())
                        .level(dto.getSender().getLevel())
                        .nickname(dto.getSender().getNickname())
                        .role(dto.getSender().getRole())
                        .time(dto.getTime())
                        .title(dto.getSender().getTitle())
                        .messageId(dto.getMessage_id())
                        .build()
        );
    }
}
