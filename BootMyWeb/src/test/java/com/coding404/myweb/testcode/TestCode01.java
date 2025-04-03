package com.coding404.myweb.testcode;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCode01 {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void testCode01(){
        for(int i=1;i<=300;i++){
            ProductVO vo=ProductVO.builder()
                        .prodEnddate("2025-12-31")
                        .prodWriter("admin")
                        .prodName("demo"+i)
                        .prodPrice(1000*i)
                        .prodCount(100*i)
                        .prodDiscount(i%100)
                        .prodPurchaseYn("Y")
                        .prodContent("demo"+i)
                        .prodComment("demo"+i)
                        .build();

            productMapper.productRegist(vo);
        }
    }
}
