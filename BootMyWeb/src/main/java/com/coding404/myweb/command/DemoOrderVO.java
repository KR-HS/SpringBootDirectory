package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoOrderVO {
    private int old;
    private int mid;
    private String productName;

    // 1st - 1관계 테이블 여러 컬림이 필요한게 아니라면 단순히 컬럼명을 n쪽에 추가하면 됨
    // private String name;

    // 2nd - 객체맵핑
    private DemoMemberVO member;
}
