package com.purchase.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Integer memberSeq;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Builder
    public Member(Integer memberSeq,
                  String memberId,
                  String memberPassword) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}
