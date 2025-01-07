package com.dionext.dioportal.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
//@Slf4j
public class IndexController {

    @GetMapping("/api/index2")
    public String index() {
        //log.info("-----------------1");
        return "Hello, this is the index endpoint!";
    }
}