package com.purchase.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_seq")
    private Integer adminSeq;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_password")
    private String adminPassword;

    @Builder
    public Admin(Integer adminSeq, String adminId, String adminPassword) {
        this.adminSeq = adminSeq;
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
}
