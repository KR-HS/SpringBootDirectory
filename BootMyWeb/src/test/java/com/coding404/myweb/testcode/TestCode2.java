package com.coding404.myweb.testcode;

import com.coding404.myweb.command.DemoMemberVO;
import com.coding404.myweb.command.DemoOrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCode2 {

    @Autowired
    TestMapper testMapper;


    @Test
    public void testCode01(){
        List<DemoOrderVO> list = testMapper.manyToOne();

        System.out.println(list.toString());
    }

    @Test
    public void testCode02(){
        List<DemoOrderVO> list = testMapper.OrderToMember();

        System.out.println(list.toString());
    }

    @Test
    public void testCode03(){
        DemoMemberVO vo = testMapper.oneToMany();

        System.out.println(vo.toString());
    }
}
