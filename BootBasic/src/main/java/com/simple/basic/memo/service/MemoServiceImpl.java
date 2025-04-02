package com.simple.basic.memo.service;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;


    @Override
    public void insert(MemoVO vo) {
        memoMapper.insert(vo);
    }

    @Override
    public void delete(int mno) {
        memoMapper.delete(mno);
    }

    @Override
    public void update(MemoVO vo) {
        memoMapper.update(vo);
    }

    @Override
    public List<MemoVO> selectAll() {
        List<MemoVO> list= memoMapper.selectAll();
        return list;
    }

    @Override
    public MemoVO selectByMno(int mno) {
        MemoVO vo = memoMapper.selectByMno(mno);
        return vo;
    }
}
