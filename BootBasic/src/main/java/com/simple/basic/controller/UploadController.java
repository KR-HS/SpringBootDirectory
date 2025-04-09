package com.simple.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {

    // 화면처리
    @GetMapping("/upload")
    public String upload(){
        return "upload/upload";
    }

    // 단일파일업로드
    @PostMapping("/upload_ok")
    public String upload_ok(@RequestParam("file") MultipartFile file){
        try {
            String originName = file.getOriginalFilename();
            String filename = originName.substring(originName.lastIndexOf("\\") + 1);
            Long filesize = file.getSize();
            byte[] filebyte = file.getBytes();
            String contentType = file.getContentType();

            log.info("파일명:"+originName);
            log.info("크기 :"+filesize);
            log.info("파일데이터:"+filebyte);
            log.info("컨텐츠 타입:"+contentType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "upload/upload_ok";
    }
}
