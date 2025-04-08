package com.coding404.myweb.controller;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestController
public class AjaxController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    // 요청파라미터 x , LIst<카테고리>반환
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryVO>> getCategory() {
        return ResponseEntity.ok( productService.getCategory());
    }

    @GetMapping("/getCategorySub/{groupId}/{categoryLv}/{categoryDetailLv}")
    public ResponseEntity<List<CategoryVO>> getCategorySub(@PathVariable("groupId") String groupId,
                                                           @PathVariable("categoryLv") Integer categoryLv,
                                                           @PathVariable("categoryDetailLv") Integer categoryDetailLv) {
        CategoryVO vo = CategoryVO.builder()
                        .groupId(groupId).categoryLv(categoryLv).categoryDetailLv(categoryDetailLv).build();
        return ResponseEntity.ok(productService.getCategorySub(vo));

    }
}
