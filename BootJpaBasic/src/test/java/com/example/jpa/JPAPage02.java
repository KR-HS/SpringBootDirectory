package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class JPAPage02 {

    @Autowired
    private MemoRepository memoRepository;


    // select에서 정렬
    @Test
    public void testCode01(){
        // Sort sort = Sort.by("text").descending(); // 컬럼명
        Sort sort = Sort.by("text").descending()
                    .and(Sort.by( "mno").descending());
        List<Memo> list = memoRepository.findAll(sort);
        System.out.println(list.toString());
    }

    // pageable객체 - 페이지 처리
    @Test
    public void testCode02(){
        Pageable pageable = PageRequest.of(0,20); // 1번 페이지 20개의 데이터

        Page<Memo> page = memoRepository.findAll(pageable);

        for(Memo m : page.getContent()){
            System.out.println(m.toString());
        }

        System.out.println("전체페이지수: "+page.getTotalPages());
        System.out.println("전체 데이터수 : " +page.getTotalElements());
        System.out.println("현재 조회하고 있는 페이지 번호 : "+ page.getNumber());
        System.out.println("amount값 : "+page.getSize());
        System.out.println("데이터의 존재여부: "+page.hasContent());
        System.out.println("이전 페이지 여부 :"+page.hasPrevious());
        System.out.println("다음 페이지 여부 :"+page.hasNext());
        System.out.println("시작페이지 여부:"+page.isFirst());
        System.out.println("마지막페이지 여부:"+page.isLast());
    }


}
