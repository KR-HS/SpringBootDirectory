package com.simple.basic.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class SimpleVO {
    private int mno;
    private String name;
    private LocalDateTime regdate;


}
