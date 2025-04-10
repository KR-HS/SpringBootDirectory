package com.simple.basic.controller;

import com.simple.basic.command.UploadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${com.coding404.myweb.upload.path}")
    private String uploadPath;

    // 폴더생성함수
    private String makeFolder(){
        String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File file = new File(uploadPath +"/"+ filepath);
        if(file.exists()==false){ // 해당위치에 파일 or 폴더가 존재하지 않으면 if문 작동
            file.mkdirs();
        }
        return filepath;
    }

    // 화면처리
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }


    // 단일파일업로드
    @PostMapping("/upload_ok")
    public String upload_ok(@RequestParam("file") MultipartFile file){

        // 1. 동일한 이름의 파일명으로 올라오면 기존 파일이 지워지는 문제 (랜덤난수명칭 이름을 바꿈)
        // 2. 각 브라우저 별로 업로드패스가 다를 수 있음
        // 3. 윈도우의 한 폴더에 최대 파일저장개수 66536개
        // 4. 확장자체크
        // 5. 사진의 크기를 똑같이 맞추는 썸네일 고려
        // filename, uuid, filepath(날짜) 이 값을 db에 저장
        try {
            String originName = file.getOriginalFilename();
            String filename = originName.substring(originName.lastIndexOf("/") + 1);
            Long filesize = file.getSize();
            byte[] filebyte = file.getBytes();
            String contentType = file.getContentType();

            UUID uuid = UUID.randomUUID(); // 16진수형태의 랜덤문자열을 반환
            String filepath = makeFolder();
            String path = uploadPath +"/"+ filepath + "/" +uuid + "_" + filename ; // 업로드패스

            File saveFile = new File(path);
            file.transferTo(saveFile); // 파일업로드를 처리

            log.info("파일명:"+originName);
            log.info("크기 :"+filesize);
            log.info("파일데이터:"+filebyte);
            log.info("컨텐츠 타입:"+contentType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "upload/upload_ok";
    }

    @PostMapping("/upload_ok2")
    public String upload2(MultipartHttpServletRequest files){
        List<MultipartFile> list= files.getFiles("file");

        for(MultipartFile file:list){
            try {
                String originName = file.getOriginalFilename();
                String filename = originName.substring(originName.lastIndexOf("/") + 1);

                UUID uuid = UUID.randomUUID(); // 16진수형태의 랜덤문자열을 반환
                String filepath = makeFolder();
                String path = uploadPath +"/"+ filepath + "/" +uuid + "_" + filename ; // 업로드패스

                File saveFile = new File(path);
                file.transferTo(saveFile); // 파일업로드를 처리
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "upload/upload_ok";
    }

    // 복수태그를 이용해서 여러파일  - 우리 프로젝트에서 예시로 사용해볼 유형
    @PostMapping("/upload_ok3")
    public String upload3(@RequestParam("file") List<MultipartFile> list){

        // 리스트안에 multipartfile요소가 값이 비었다면 제거
        log.info(list.toString());
        list = list.stream().filter(f-> !f.isEmpty()).collect(Collectors.toList());
        log.info(list.toString());

        for(MultipartFile file:list){
            try {
                String originName = file.getOriginalFilename();
                String filename = originName.substring(originName.lastIndexOf("/") + 1);

                UUID uuid = UUID.randomUUID(); // 16진수형태의 랜덤문자열을 반환
                String filepath = makeFolder();
                String path = uploadPath +"/"+ filepath + "/" +uuid + "_" + filename ; // 업로드패스

                File saveFile = new File(path);
                file.transferTo(saveFile); // 파일업로드를 처리
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "upload/upload_ok";
    }

    // ajax요청을 받는 함수
    // 값을 받을떄는 @RequestParam쓰거나 VO객체 맵핑
    @ResponseBody
    @PostMapping("/upload_ok4")
    public String upload4(UploadVO vo){
        MultipartFile file = vo.getFile();
        try {
            String originName = file.getOriginalFilename();
            String filename = originName.substring(originName.lastIndexOf("/") + 1);

            UUID uuid = UUID.randomUUID(); // 16진수형태의 랜덤문자열을 반환
            String filepath = makeFolder();
            String path = uploadPath +"/"+ filepath + "/" +uuid + "_" + filename ; // 업로드패스

            File saveFile = new File(path);
            file.transferTo(saveFile); // 파일업로드를 처리
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "upload/upload_ok";
    }

}
