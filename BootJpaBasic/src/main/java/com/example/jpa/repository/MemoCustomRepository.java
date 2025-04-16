package com.example.jpa.repository;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.MemberMemoDTO;
import com.example.jpa.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoCustomRepository {
    int updateTest(String writer, Long mno);

    //ManyToOne
    List<Memo> mtoJoin1(Long mno);
    // ManyToOne - 연관관계가 없는 경우 on 절
    List<Memo> mtoJoin3(String name);


    // oneToMany - default조인방식 = Lazy
    Member otmJoin1(String id);

    //DTO로 반환받기
    List<MemberMemoDTO> getList(String id);

    List<MemberMemoDTO> quiz2(String text);
}
