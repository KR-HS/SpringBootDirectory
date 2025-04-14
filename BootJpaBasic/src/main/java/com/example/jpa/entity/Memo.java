package com.example.jpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity//JPA가 관리함
@Table(name = "MEMO")//테이블명
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Memo {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increament를 대신함
    //시퀀스에 대한 전략
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//    @SequenceGenerator(name = "my_seq", sequenceName = "my_sequence", allocationSize = 1)
    private long mno;
    @Column(nullable = false, length = 100)
    private String writer;
    @Column(columnDefinition = "varchar(200) default 'y'")
    private String text;
}