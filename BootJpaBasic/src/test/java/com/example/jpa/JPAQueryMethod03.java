package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JPAQueryMethod03 {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void test01(){
        List<Memo> list = memoRepository.findByMnoBetween(10L,20L);

        System.out.println(list.toString());
    }

    @Test
    public void test02(){
        List<Memo> list = memoRepository.findByTextOrderByMnoDesc("sample5");
        System.out.println(list.toString());
    }

    @Test
    public void test03(){
        List<String> find = Arrays.asList("admin3","admin85","admin107");
        List<Memo> list = memoRepository.findByWriterIsInOrderByMnoAsc(find);

        System.out.println(list.toString());
    }

    @Test
    public void test04(){
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(10L,20L);
        System.out.println(list.toString());
    }

    @Test
    public void test05(){
        List<Memo> list = memoRepository.findByTextContainingOrWriterContainingOrderByMnoDesc("sample5","admin1");

        System.out.println(list.toString());
    }


    @Test
    public void test06(){
        Pageable pageable = PageRequest.of(0, 10);

        String a = "%5%";
        String b = "%6%";
        Page<Memo> page = memoRepository.findByTextLikeOrWriterLike(a,b,pageable);

        System.out.println(page.getContent().toString());
        System.out.println(page.getTotalElements());
    }
}
