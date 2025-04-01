package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidVO {

    /*
        @NotNull - Null값 제외 - Integer,Long,String적용
        @NotEmpty - Null제외, 공백허용 x - Array, Map , String 적용
        @NotBlank - Null제외, 공백허용 x, 화이트스페이스 허용x - String 적용
        
        @Pattern - 정규표현식으로
        @Email - 기본으로 제공되는 이메일 검사
        @Size  - 크기 검사... 등
     */

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotNull(message = "급여는 필수입니다")
    private Integer salary;

    @Pattern(regexp = "01[0-9]{1}-[0-9]{4}-[0-9]{4}",message = "휴대폰 번호는 010-xxxx-xxxx유형입니다")
    private String phone;

    @NotBlank
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
}
