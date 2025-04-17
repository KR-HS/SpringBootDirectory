package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPAQueryDsl06 {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testCode01(){
        Memo memo = memoRepository.selectDsl();
        System.out.println(memo);
    }

    @Test
    public void testCode02(){
        List<Memo> list = memoRepository.selectDsl2();
        System.out.println(list.toString());
    }

    @Test
    public void testCode03(){
        List<Memo> list = memoRepository.selectDsl3("writer","5");
        System.out.println(list.toString());
    }

}
