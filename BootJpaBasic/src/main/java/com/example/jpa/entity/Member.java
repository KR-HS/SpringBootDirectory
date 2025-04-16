package com.example.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MEMBER")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String id;
    @Column(nullable =false, length=50)
    private String name;
    // 기본으로 지정되는 날짜
    @CreatedDate // JPA를 통해서 날짜가 자동입력되는 어노테이션(오디팅리스너와 함께 사용)
    @Column(updatable = false) // JPA에 의해서 자동으로 변경되는것을 막음
    private LocalDateTime signDate; // 가입일

    //원투매니 조인 - toString을 한쪽에서 지움
    // default방식은 lazy( 조인을 걸고 필요한 시점에 붙일 table을 셀렉트하는 방식)
    // -> 실행 메서드에서 transactional을 반드시 붙임
    // 또는 조인방식 eager ( 즉시 조인을 걸어서 수행을 함)
//    @OneToMany(fetch=FetchType.EAGER)
//    @JoinColumn(name="member_id")
//    List<Memo> list  = new ArrayList<>();
    
    
    // 양방향 조인
    // mapppered는 연관관게주인 - 메모테이블의 member변수를 의미
    @OneToMany(mappedBy="member", fetch = FetchType.LAZY)
    List<Memo> list = new ArrayList<>();
}
