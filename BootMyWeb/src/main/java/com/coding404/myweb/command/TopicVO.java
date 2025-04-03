package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicVO {
    private int topicId;
    private Timestamp topicRegdate;
    private String topicWriter;
    private String topicContent;
    private String topicComment;
}
