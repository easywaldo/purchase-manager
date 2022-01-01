package com.purchase.member.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginCommand {
    private String memberId;
    private String memberPassword;

    @Builder
    public LoginCommand(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}
