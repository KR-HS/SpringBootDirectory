package com.example.jpa.repository;

import com.example.jpa.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoCustomRepository {
    int updateTest(String writer, Long mno);

    //ManyToOne
    List<Memo> mtoJoin1(Long mno);
}
