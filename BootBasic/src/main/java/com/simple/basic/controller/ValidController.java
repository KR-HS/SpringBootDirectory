package com.simple.basic.controller;

import com.simple.basic.command.QuizVO;
import com.simple.basic.command.ValidVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/valid")
public class ValidController {

    // 화면처리
    @GetMapping("/view")
    public String view(){
        return "valid/view";
    }

    // 결과화면 화면처리
    @GetMapping("/result")
    public String result(){
        return "valid/result";
    }

    //가입요청
    @PostMapping("/joinForm")
    public String joinForm(@Valid @ModelAttribute("vo") ValidVO vo, BindingResult result, Model model /*, RedirectAttributes redirectAttributes*/){
        // 유효성 검사를 진행해서 실패한 목록들을 BindingResult에 담아줌

        // 1st
//        if(result.hasErrors()){ // 유효성검사에 실패하면 true
//            List<FieldError> list = result.getFieldErrors(); // 실패 목록
//            for(FieldError err : list){
//                System.out.println(err.getField());
//                System.out.println(err.getDefaultMessage());
//                model.addAttribute("valid_"+err.getField(),err.getDefaultMessage());
//                redirectAttributes.addFlashAttribute("valid_"+err.getField(),err.getDefaultMessage());
//            }
//            redirectAttributes.addFlashAttribute("vo",vo);
//            return "redirect:/valid/view";
//
//        }
        // 2nd
        if(result.hasErrors()){
            return "valid/view";
        }

        return "redirect:/valid/result";
    }

    @GetMapping("/quiz01")
    public String quiz01(){
        return "valid/quiz01";
    }

    @GetMapping("/quiz01_result")
    public String quiz01_result(){
        return "valid/quiz01_result";
    }

    @PostMapping("/quiz01_loginForm")
    public String quiz01_result(@Valid @ModelAttribute("vo") QuizVO vo, BindingResult result){

        if(result.hasErrors()){
            return "valid/quiz01";
        }
        return "redirect:/valid/quiz01_result";

    }
}
