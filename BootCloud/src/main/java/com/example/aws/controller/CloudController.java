package com.example.aws.controller;

import com.example.aws.component.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CloudController {

    @Autowired
    private S3Service s3Service;

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    //S3연결확인 기능
    @GetMapping("/S3Request")
    public String S3Request() {
        s3Service.getBucketList();
        return "redirect:/main";
    }


}
