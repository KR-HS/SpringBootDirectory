package com.coding404.myweb.product.mapper;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    boolean productRegist(ProductVO vo);
    List<ProductVO> getList(@Param("prodWriter") String prodWriter,@Param("cri") Criteria cri);
    int getTotal(@Param("prodWriter") String prodWriter,@Param("cri") Criteria cri);

    ProductVO getDetail(int prodId);
    boolean productUpdate(ProductVO vo);
    boolean productDelete(int prodId);

    //카테고리
    List<CategoryVO> getCategory();
    List<CategoryVO> getCategorySub(CategoryVO vo);

    // 업로드 인서트
    boolean productRegistFile(ProductUploadVO vo);

}
