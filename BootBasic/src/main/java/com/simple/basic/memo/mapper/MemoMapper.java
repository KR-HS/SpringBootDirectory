package com.simple.basic.memo.mapper;

import com.simple.basic.command.MemoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 반드시 붙여야함
public interface MemoMapper {
    String getTime();
    int insert(MemoVO vo);
    int delete(int mno);
    int update(MemoVO vo);
    MemoVO selectByMno(int mno);
    List<MemoVO> selectAll();

}
