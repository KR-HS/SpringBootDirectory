package com.simple.basic.controller;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor //클래스의 final 필드와 @NonNull 필드를 기반으로 생성자를 생성 ( 생성자 자동생성 )
public class MemoController {

    @Autowired
    private final MemoService memoService;

    @GetMapping("/memoList")
    public String memoList(Model model){
        List<MemoVO> list = memoService.selectAll();
        model.addAttribute("list",list);
        return "memo/memoList";
    }

    @GetMapping("/memoWrite")
    public String memoWrite(){
        return "memo/memoWrite";
    }

    @PostMapping("/memoForm")
    public String memoForm(@Valid @ModelAttribute("vo") MemoVO vo, BindingResult result){
        if(result.hasErrors()){
            return "memo/memoWrite";
        }
        System.out.println("검사 완료");
        memoService.insert(vo);
        System.out.println("인서트완료");
        return "redirect:/memo/memoList";

    }
}
