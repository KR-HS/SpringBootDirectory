package com.simple.basic.memo.service;

import com.simple.basic.command.MemoVO;

import java.util.List;

public interface MemoService {

    void insert(MemoVO vo);
    void delete(int mno);
    void update(MemoVO vo);
    List<MemoVO> selectAll();
    MemoVO selectByMno(int mno);
}
