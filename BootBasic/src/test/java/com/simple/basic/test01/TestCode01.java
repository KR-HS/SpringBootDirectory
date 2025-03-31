package com.simple.basic.test01;

import com.simple.basic.command.TestBuilderVO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트코드의 순서를 지정할 때 사용함
public class TestCode01 {

    //여러 테스트들 중에서 하나라도 실패하면, 서버 빌드에 실패를 하게됩니다.
    @Autowired
    public ApplicationContext applicationContext;

    @Test
//    @Order(2)
    public void test01() {
        System.out.println("테스트코드");
        System.out.println("빈의개수:" + applicationContext.getBeanDefinitionCount() );
    }
//
//    @Test
//    @Order(1)
//    public void test02() {
//        System.out.println("테스트코드2");
//    }


    // 빌더 패턴의 사용
    @Test
    public void test03(){
        TestBuilderVO vo  = TestBuilderVO.builder().age(20).name("홍길동").build();

        System.out.println(vo.toString());
    }
}
