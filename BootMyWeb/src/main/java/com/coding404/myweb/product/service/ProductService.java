package com.coding404.myweb.product.service;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    boolean productRegist(ProductVO vo, List<MultipartFile> files);
    List<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter,Criteria cri);

    ProductVO getDetail(int prodId);
    boolean productUpdate(ProductVO vo);
    boolean productDelete(int prodId);



    List<CategoryVO> getCategory(); // 1단 select(대분류)
    List<CategoryVO> getCategorySub(CategoryVO vo); // 2단,3단 소분류

    // 파일정보조회
    List<ProductUploadVO> getDetailImg(String prodId);

}
