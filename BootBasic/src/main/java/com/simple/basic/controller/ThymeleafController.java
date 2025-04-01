package com.simple.basic.controller;

import com.simple.basic.command.SimpleVO;
import com.simple.basic.command.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/view")
public class ThymeleafController {

    @GetMapping("/ex01")
    public String ex01(){
        return "view/ex01";// templates폴더 하위 경로
    }

    @GetMapping("/ex02")
    public String ex02(Model model){

        ArrayList<TestVO> list = new ArrayList<>();

        for(int i=1;i<=10;i++){
            TestVO vo = TestVO
                        .builder()
                        .id("aaa"+i)
                        .name("홍길동"+i)
                        .address("서울시"+i)
                        .hiredate(LocalDateTime.now())
                        .salary(3000+i)
                        .build();
            list.add(vo);
        }
        model.addAttribute("list",list);
        return "view/ex02";
    }

    @GetMapping("/ex03")
    public String ex03(Model model){

        ArrayList<TestVO> list = new ArrayList<>();

        for(int i=1;i<=10;i++){
            TestVO vo = TestVO
                    .builder()
                    .id("aaa"+i)
                    .name("홍길동"+i)
                    .address("서울시"+i)
                    .hiredate(LocalDateTime.now())
                    .salary(3000+i)
                    .build();
            list.add(vo);
        }
        model.addAttribute("list",list);
        return "view/ex03";
    }
    @GetMapping("/result")
    public String result(){
        return "view/result";
    }

    // 파라미터 넘기는 2번째 방법 @PathVariable
    @GetMapping("/result2/{address}/{value}")
    public String result2(@PathVariable("address") String address,
                          @PathVariable("value") String value){
        System.out.println(address + ", "+value);
        return "view/result";
    }

    @GetMapping("/ex04")
    public String ex04(Model model){

        TestVO vo = new TestVO();
        vo.setId("abc123");
        vo.setAddress("경기도");
        vo.setName("홍길동");
        vo.setHiredate(LocalDateTime.now());
        model.addAttribute("vo",vo);

        return "view/ex04";
    }

    @GetMapping("/ex05")
    public String ex05(){
        return "view/ex05";
    }

    @GetMapping("/ex06")
    public String ex06(){
        return "view/ex06";
    }

    @GetMapping("/quiz01")
    public String quiz01(){

        return "view/quiz01";
    }
    @GetMapping("/quiz01_result")
    public String quiz01_result(Model model, @RequestParam("name") String name,@RequestParam("mno") int mno){
        SimpleVO vo = SimpleVO.builder().
                        name(name).
                        mno(mno).
                        regdate(LocalDateTime.now()).
                        build();
        model.addAttribute("vo",vo);
        return "view/quiz01_result";
    }

}
