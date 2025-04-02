package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice1")
public class Practice1Controller {

    @GetMapping("/topicDetail")
    String topicDetail() {
        return "practice1/topicDetail";
    }

    @GetMapping("/topicListAll")
    String topicListAll() {
        return "practice1/topicListAll";
    }

    @GetMapping("topicListMe")
    String topicListMe() {
        return "practice1/topicListMe";
    }

    @GetMapping("topicModify")
    String topicModify() {
        return "practice1/topicModify";
    }

    @GetMapping("topicReg")
    String topicReg() {
        return "practice1/topicReg";
    }


}
