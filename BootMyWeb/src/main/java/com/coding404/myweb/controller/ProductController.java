package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;


    //화면처리
//    @GetMapping("/productList")
//    public String productList(Model model) {
//        List<ProductVO> list = productService.selectByWriter("admin");
//        log.info(list.toString());
//        model.addAttribute("list",list);
//        return "product/productList";
//    }

    @GetMapping("/productList")
    public String productList(Model model, Criteria ct){
        List<ProductVO> list = productService.getList("admin",ct);
        log.info(list.toString());
        int total = productService.getTotal("admin",ct);
        PageVO pageVO = new PageVO(ct,total);

        model.addAttribute("list",list);
        model.addAttribute("pageVO",pageVO);
        return "product/productList";
    }

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") int prodId, Model model) {
        // prodId를 받아서 조회
        ProductVO vo = productService.getDetail(prodId);
        model.addAttribute("vo",vo);
        return "product/productDetail";
    }

    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    @PostMapping("/registForm")
    public String registForm(ProductVO vo, RedirectAttributes ra) {

        log.info(vo.toString());
        boolean result = productService.productRegist(vo);

        if (result) {
            ra.addFlashAttribute("msg","정상 등록 되었습니다.");
        }else{
            ra.addFlashAttribute("msg","등록에 실패했습니다.");
        }

        return "redirect:/product/productList";
    }

    @PostMapping("/updateForm")
    public String updateForm(ProductVO vo, RedirectAttributes ra) {
        boolean result = productService.productUpdate(vo);
        if(result)  ra.addFlashAttribute("msg","수정되었습니다");
        else ra.addFlashAttribute("msg","수정에 실패했습니다.");
        return "redirect:/product/productDetail?prodId="+vo.getProdId(); // 수정하고 컨텐츠화면으로
    }
    @PostMapping("/deleteForm")
    public String deleteForm(@RequestParam("prodId") int prodId, RedirectAttributes ra) {
        boolean result = productService.productDelete(prodId);
        if(result)  ra.addFlashAttribute("msg","삭제되었습니다");
        else {
            ra.addFlashAttribute("msg","삭제에 실패했습니다.");
            return "redirect:/product/productDetail?prodId="+prodId;
        }
        return "redirect:/product/productList";
    }



}
