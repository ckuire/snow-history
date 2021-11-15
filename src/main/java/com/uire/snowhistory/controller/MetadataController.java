package com.uire.snowhistory.controller;

import com.uire.snowhistory.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author uire
 * @date 2021/11/04 17:03
 **/
@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;

    @PostMapping
    public void post(@RequestBody String metadata) {
        metadataService.index(metadata);
    }
}
