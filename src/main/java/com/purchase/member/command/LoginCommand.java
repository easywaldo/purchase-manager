package com.purchase.member.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginCommand {
    @ApiModelProperty(value = "회원 아이디", position = 1)
    @NotBlank
    private String memberId;
    @ApiModelProperty(value = "회원 비밀번호", position = 2)
    @NotBlank
    private String memberPassword;

    @Builder
    public LoginCommand(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}
