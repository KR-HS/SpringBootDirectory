package com.coding404.myweb.product.mapper;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    boolean productRegist(ProductVO vo);
    List<ProductVO> getList(@Param("prodWriter") String prodWriter,@Param("cri") Criteria cri);
    int getTotal(String prodWriter);

    ProductVO getDetail(int prodId);
    boolean productUpdate(ProductVO vo);
    boolean productDelete(int prodId);
}
