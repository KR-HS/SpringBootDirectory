package com.simple.basic.util.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 1. 핸들러 인터셉터 상속
public class UserAuthHandler implements HandlerInterceptor {

    // 2.오버라이딩
    // pre - 컨트롤러 진입전
    // post - 컨트롤러 실행이후
    //after 컴플리션 - 리졸버 뷰까지 실행된 이후에 동작

    //3. 스프링 설정파일에 인터셉터를 등록

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 리턴에 true가 들어가면 controller를 실행함, false가 들어가면 컨트롤러 실행 안함
        System.out.println("컨트롤러 실행전 인터셉터 동작");
        
        // 세션이 존재여부를 확인해서 세션이 없으면 돌려보냄

        // 리퀘스트에서 세션얻음
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username"); // 로그인 만든사람이 지정한 값

        if(username == null){
            // 인증되지않음
            response.sendRedirect("/user/login"); // 로그인페이지로 리다이렉트
            return false; // 컨트롤러를 실행하지 않음
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("컨트롤러 실행 후 인터셉터 동작");
    }
}
