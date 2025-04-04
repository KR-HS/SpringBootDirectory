package com.simple.basic.controller;

import com.simple.basic.command.SimpleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@RestController // 일반 컨트롤러와는 다른의미 - return에 담기는 데이터가 요청한 곳으로 응답함
public class RestBasicController {
    @GetMapping("/hello")
    public String hello() {
        return "<h3>hello</h3>";
    }

    // 데이터를 보내는 방법
    // 1. 객체 반환
    //ResponseBody와 JSon-databind라이브러리가 해줍니다(자동으로)
    @GetMapping("/bye")
    public SimpleVO bye() {
        return new SimpleVO(1, "홍길동", LocalDateTime.now());
    }

    // 2. 맵을 반환
    @GetMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "홍길동");
        map.put("age", 200);
        map.put("data", new SimpleVO(1, "홍길동", LocalDateTime.now()));
        return map;
    }

    // 3. 리스트로 반환
    @GetMapping("/getList")
    public List<SimpleVO> getList() {
        List<SimpleVO> list = new ArrayList<>();
        list.add(new SimpleVO(1, "홍길동", LocalDateTime.now()));
        list.add(new SimpleVO(2, "홍길자", LocalDateTime.now()));
        return list;
    }

    // 값을 받는 방법
    // 요청의 다양한 타입 get
    // @RequestParam이나, VO를 통해서 받을 수 있음
    //
    @GetMapping("/getData")
    public String getData(@RequestParam(required = false) String name,
                          @RequestParam(required = false) int sno,
                          HttpServletRequest request) {
        log.info(request.getRemoteAddr());
        log.info(name + "," + sno);
        return "success";
    }

    @GetMapping("/getData2")
    public String getData2(SimpleVO vo) {
        log.info(vo.toString());
        return "success";
    }

    @GetMapping("/getData3/{name}/{sno}")
    public String getData3(@PathVariable("name") String name,
                           @PathVariable("sno") int sno) {
        log.info(name + "," + sno);
        return "success";
    }

    /// ////////////////////////////////////////
    // POST방식으로 데이터 받기
    // 상대방이  데이터를 보내는 contentType을 지정함
    // form타입 , JSON타입

    // 보내는 입장이 form형식으로 보내는 경우
//    @PostMapping("/getForm")
//    public String getForm(SimpleVO vo){
//        log.info(vo.toString());
//        return "success";
//    }
    @PostMapping("/getForm")
    public String getForm(@RequestParam("name") String name,
                          @RequestParam("mno") String mno) {
        log.info(name + "," + mno);
        return "success";
    }


    // 보내는 입장이 JSON타입으로 보내는 경우 -> VO또는 MAP타입으로
    // @RequestBody -> JSON데이터를 Object맵핑
    // { "name" : "홍길동" , "age" : 1 }
//    @PostMapping("/getJson")
//    public String getJson(@RequestBody SimpleVO vo){
//        log.info(vo.toString());
//        return "success";
//    }
    @PostMapping("/getJson")
    public String getJson(@RequestBody Map<String, Object> map) {
        log.info(map.toString());
        return "success";
    }

    ////////////////////////////////////

    // produces - 서버에서 보내는 타입에 대한 명세 ( 아무것도 안적으면 기본 JSON타입)
    // consumes - 서버에 보내는 타입에 대한 명세
    @PostMapping(value = "/getResult",
            produces = "text/plain",
            consumes = "multipart/form-data")
    public String getResult(@RequestBody String str) {
        log.info(str);

        return "<h3>성공</h3>";
    }

    // 응답문서 작성하기 ResponseEntity<보낼데이터타입>

    @PostMapping("/createEntity")
    public ResponseEntity<SimpleVO> createEntity() {
        SimpleVO vo = new SimpleVO(2,"홍길동", LocalDateTime.now());
        // 응답문서에 헤더에 대한 내역을 추가할 수 있음
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","application/json");
        headers.add("authorization","JSON WEB TOKEN");
        headers.add("Access-control-allow-origin","*");
        return new ResponseEntity<>(vo, headers,HttpStatus.OK);
    }

    ////////////////////////////////////
    // 명세에 맞춰 작성후 부메랑으로 호출
    /*

    요청 주소  - /api/v1/getData
    메서드 - get
    클라이언트에서 보내는 데이터 - num,name

    서버 응답 데이터 SimpleVO
    responseEntity로 응답
     */
    @GetMapping("api/v1/getData")
    public ResponseEntity<SimpleVO> getData(@RequestParam("num")int num,
                                            @RequestParam("name")String name) {
        SimpleVO vo = new SimpleVO(num,name, LocalDateTime.now());

        return new ResponseEntity<>(vo,HttpStatus.OK);
    }

    /*
       요청주소  - /api/v1/getInfo
       메서드 - post
       클라이언트에서 보내는 데이터 - JSON 타입의 num,name
       서버에서 응답하는 데이터 - List<SimpleVO>
       responseEntity로 응답
     */
    @PostMapping(value= "api/v1/getInfo",
    consumes="application/json")
    public ResponseEntity<List<SimpleVO>> getInfo(@RequestBody Map<String,Object> map) {
        List<SimpleVO> list = new ArrayList<>();
        list.add(new SimpleVO((int)map.get("num"),(String)map.get("name"),LocalDateTime.now()));

        return ResponseEntity.ok(list);
    }
}
