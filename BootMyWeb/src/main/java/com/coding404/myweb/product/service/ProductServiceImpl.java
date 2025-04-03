package com.coding404.myweb.product.service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.mapper.ProductMapper;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService") // 멤버변수명과 동일한 이름
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public boolean productRegist(ProductVO vo) {
        return productMapper.productRegist(vo);
    }

    @Override
    public List<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter,cri);
    }

    @Override
    public int getTotal(String prodWriter) {
        return productMapper.getTotal(prodWriter);
    }

    @Override
    public ProductVO getDetail(int prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public boolean productUpdate(ProductVO vo) {
        return productMapper.productUpdate(vo);
    }

    @Override
    public boolean productDelete(int prodId) {
        return productMapper.productDelete(prodId);
    }
}
