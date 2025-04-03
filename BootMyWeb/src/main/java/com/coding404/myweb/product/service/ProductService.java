package com.coding404.myweb.product.service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

import java.util.List;

public interface ProductService {
    boolean productRegist(ProductVO vo);
    List<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter);

    ProductVO getDetail(int prodId);
    boolean productUpdate(ProductVO vo);
    boolean productDelete(int prodId);
}
