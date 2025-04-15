package com.example.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MEMBER")
@EntityListeners(AuditingEntityListener.class)
@Data
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
}
