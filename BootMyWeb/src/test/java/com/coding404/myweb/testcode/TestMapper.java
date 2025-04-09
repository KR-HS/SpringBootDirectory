package com.coding404.myweb.testcode;

import com.coding404.myweb.command.DemoMemberVO;
import com.coding404.myweb.command.DemoOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<DemoOrderVO> manyToOne(); // N:1조인
    List<DemoOrderVO> OrderToMember();
    DemoMemberVO oneToMany();// 1:N조인
}
