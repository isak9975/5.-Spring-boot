package com.korea.todo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//SimpleUrlAuthenticationSuccessHandler
//인증 성공 후 사용자를 처리하는데 사용되는 클래스.
@Component
@Slf4j
@AllArgsConstructor
public class OAuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler{

	//스프링 시큐리티가 OAuth2 로그인에 성공한 직후 호출하는 메서드
	//Authentication객체에 로그인된 사용자 정보가 포함되어 있다.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//토큰 발급
		TokenProvider tokenProvider = new TokenProvider();
		String token = tokenProvider.create(authentication);
		
		response.getWriter().write(token);
		log.info("token {}",token);
	}
	
}
