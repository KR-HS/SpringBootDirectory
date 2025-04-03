package com.coding404.myweb.topic.service;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;

import java.util.List;

public interface TopicService {

    List<TopicVO> getAllList(Criteria cri);
    int getTotal();
}
