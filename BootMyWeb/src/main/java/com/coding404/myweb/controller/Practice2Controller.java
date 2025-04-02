package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice2")
public class Practice2Controller {

    @GetMapping("noticeDetail")
    public String noticeDetail() {
        return "practice2/noticeDetail";
    }

    @GetMapping("noticeList")
    public String noticeList() {
        return "practice2/noticeList";
    }

    @GetMapping("noticeReg")
    public String noticeReg() {
        return "practice2/noticeReg";
    }
}
