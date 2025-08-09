package com.itzhai.demo.pub.logger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private static final Logger log = LoggerFactory.getLogger(LogTestController.class);
    @GetMapping("/server-timestamp")
    public Long getServerTimestamp() {
        log.info("Get current server time");
        return System.currentTimeMillis();
    }
}