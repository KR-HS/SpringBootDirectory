package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPACustom05 {

    @Autowired
    private MemoRepository memoRepository;


    @Test
    public void test01(){
        int result = memoRepository.updateTest("커스텀레포지토리",100L);
        System.out.println("업데이터 성공여부:"+result);
    }

    /// ///////////
    // 매니투원 조인
    @Test
    public void test02(){
        List<Memo> list = memoRepository.mtoJoin1(2L);

        for(Memo memo : list){
            System.out.println(memo.toString());
        }
    }
}
