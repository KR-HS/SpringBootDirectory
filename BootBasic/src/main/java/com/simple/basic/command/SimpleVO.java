package com.simple.basic.command;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleVO {
    private int mno;
    private String name;
    private LocalDateTime regdate;

}
