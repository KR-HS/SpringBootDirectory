package com.coding404.myweb.controller;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.topic.service.TopicService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/practice1")
public class Practice1Controller {

    @Autowired
    @Qualifier("topicService")
    private TopicService topicService;

    @GetMapping("/topicDetail")
    String topicDetail() {
        return "practice1/topicDetail";
    }

    @GetMapping("/topicListAll")
    String topicListAll(Model model, Criteria cri) {

        List<TopicVO> list = topicService.getAllList(cri);
        int total = topicService.getTotal();
        PageVO pageVO = new PageVO(cri,total);
        model.addAttribute("list",list);
        model.addAttribute("pageVO",pageVO);

        return "practice1/topicListAll";
    }

    @GetMapping("topicListMe")
    String topicListMe() {
        return "practice1/topicListMe";
    }

    @GetMapping("topicModify")
    String topicModify() {
        return "practice1/topicModify";
    }

    @GetMapping("topicReg")
    String topicReg() {
        return "practice1/topicReg";
    }


}
