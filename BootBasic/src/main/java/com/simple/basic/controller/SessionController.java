package com.simple.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class SessionController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session) {
        if(session.getAttribute("username")==null)
            return "redirect:/user/login";
        return "user/mypage";
    }

    @GetMapping("/success")
    public String success(HttpSession session) {
        if(session.getAttribute("username")==null)
            return "redirect:/user/login";

        return "user/success";
    }

    // 로그인시도함수
    @PostMapping("/loginForm")
    public String loginForm(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {

        // 디비 로그인 시도
        // select * from 유저 where user= 값 and password = 값
        if(username.equals(password)){
            // 서버에서 인증수단으로 session발급
            session.setAttribute("username",username);
            session.setAttribute("password",password);


            return "redirect:/user/success";
        }else{
            return "redirect:/user/login";
        }
    }
}
