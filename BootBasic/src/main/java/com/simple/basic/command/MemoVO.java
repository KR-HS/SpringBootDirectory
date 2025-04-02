package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoVO {

    private Integer mno;
    @Pattern(regexp = "[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,.!@#$%^&*()\\s\\\"\\']{5,}",message = "5글자 이상 입력해주세요")
    private String memo;
    @Pattern(regexp = "01[0-9]{1}-[0-9]{4}-[0-9]{4}",message = "휴대폰 형식은 01X-XXXX-XXXX입니다.")
    private String phone;
    @Pattern(regexp = "[0-9]{4}",message="비밀번호는 숫자 4자리입니다.")
    private String pw;
    @Pattern(regexp = "[yn]{1}",message="값을 체크해주세요")
    private String secret;
}
