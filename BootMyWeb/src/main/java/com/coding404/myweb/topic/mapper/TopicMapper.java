package com.coding404.myweb.topic.mapper;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {
    List<TopicVO> getAllList(@Param("cri") Criteria cri);
    int getTotal();
}
