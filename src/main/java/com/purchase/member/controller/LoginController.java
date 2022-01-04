package com.purchase.member.controller;

import com.google.common.base.Strings;
import com.purchase.member.command.LoginCommand;
import com.purchase.member.service.AuthService;
import com.purchase.member.service.CookieService;
import com.purchase.member.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {
    private final AuthService authSerivce;
    private final LoginService loginService;

    @Autowired
    public LoginController(AuthService authSerivce,
                           LoginService loginService) {
        this.authSerivce = authSerivce;
        this.loginService = loginService;
    }

    @ApiOperation(value = "회원 로그인에 대한 요청 및 결과를 리턴 합니다.", notes = "로그인 완료 시  회원인증 JWT 가 생성이 되어 쿠키에 설정됩니다.")
    @PostMapping(value = "/member/login")
    public Mono<ResponseEntity<?>> login(@RequestBody LoginCommand loginCommand,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        var token = CookieService.getToken(httpServletRequest);
        boolean isExistMember = this.loginService.isExistMember(loginCommand);

        if (!isExistMember) {
            return Mono.just(ResponseEntity.badRequest().body(false));
        }

        if (Strings.isNullOrEmpty(token)) {
            var issuedToken = authSerivce.issueToken(loginCommand.getMemberId());
            httpServletResponse.addCookie(CookieService.addCookie("userJwt", issuedToken));

        }
        else {
            var authResult = authSerivce.validateToken(token);
            if (authResult.get("name").equals("")) {
                CookieService.deleteCookie("userJwt");
                return Mono.just(ResponseEntity.badRequest().body(false));
            }
        }

        return Mono.just(ResponseEntity.ok().body(true));
    }

    @ApiOperation(value = "회원 로그아웃에 대한 요청과 결과를 리턴합니다.", notes = "로그아웃 시 쿠키의 유저토큰을 삭제합니다.")
    @DeleteMapping(value = "/member/logout")
    public boolean login(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) {
        CookieService.deleteCookie("userJwt");
        return false;
    }
}
