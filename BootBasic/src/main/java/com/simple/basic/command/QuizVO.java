package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizVO {

    @Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "아이디 형식이 일치하지 않습니다")
    private String id;

    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}",message = "비밀번호 형식이 일치하지 않습니다")
    private String pw;
}
