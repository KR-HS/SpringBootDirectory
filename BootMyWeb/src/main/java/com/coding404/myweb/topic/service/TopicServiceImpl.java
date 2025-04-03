package com.coding404.myweb.topic.service;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.mapper.TopicMapper;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicVO> getAllList(Criteria cri) {
        return topicMapper.getAllList(cri);
    }

    @Override
    public int getTotal() {
        return topicMapper.getTotal();
    }
}
