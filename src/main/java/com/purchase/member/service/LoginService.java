package com.purchase.member.service;

import com.purchase.member.command.LoginCommand;
import com.purchase.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public boolean isExistMember(LoginCommand loginCommand) {
        var member =this.memberRepository.findByMemberId(loginCommand.getMemberId());
        if (member.isEmpty()) return false;
        return member.get().getMemberPassword().equals(loginCommand.getMemberPassword());
    }
}
