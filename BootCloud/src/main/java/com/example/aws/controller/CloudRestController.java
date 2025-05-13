package com.example.aws.controller;

import com.example.aws.component.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CloudRestController {

    @Autowired
    private S3Service s3Service;

    //버킷 업로드
    @PostMapping("/cloudUpload")
    public String cloudUpload(@RequestParam("file_data")MultipartFile file) {

        s3Service.uploadBucket(file);

        return "응답에 대한 사후처리는 여러분이 알아서 하세요";
    }

    //버킷 객체목록 확인
    @GetMapping("/list_bucket_objects")
    public String list_bucket_objects() {

        s3Service.list_bucket_objects();

        return "응답에 대한 사후처리는 여러분이 알아서 하세요";
    }


    // 버킷객체 삭제
    @DeleteMapping("/delete_bucket_objects")
    public String delete_bucket_object(@RequestParam("bucket_obj_name") List<String> bucket_obj_name) {


        System.out.println(bucket_obj_name);
        s3Service.delete_bucket_object(bucket_obj_name);

        return "응답에 대한 사후처리는 여러분이 알아서 하세요";
    }

}
