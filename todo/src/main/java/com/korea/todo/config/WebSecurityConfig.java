package com.korea.todo.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.korea.todo.security.JwtAuthenticationFilter;
import com.korea.todo.security.OAuthSucessHandler;
import com.korea.todo.security.OAuthUserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터 체인과 설정을 활성화 한다.
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	//필터 클래스 주입하기
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired private OAuthUserServiceImpl oAuthUserServiceImpl;
	
	@Autowired private OAuthSucessHandler oAuthSucessHandler;
	
	//HttpSecurity http
	//스프링 시큐리티에서 웹 보안 설정을 구성하기 위해 제공하는 보안 빌더 객체이다.
	//이 객체에 여러 보안 옵션을 메서드 체이닝 방식으로 선언하면, 최종적으로 필터체인이 생성된다.
	
	@Bean //Bean 으로 등록해주는 어노테이션
	protected DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			//(Cross-Site Request Forgery) : 사용자가 의도하지 않은 요청이
			//공격자에 의해 전소오디는 것을 방지하는 보안 매커니즘.
			.csrf(csrfConfigurer -> csrfConfigurer.disable())
			//httpBasic 또는 Form 기반 로그인 설정을 활성화/비활성화 한다.
			.httpBasic(httpBasicConfigurer -> httpBasicConfigurer.disable())
			//스프링 시큐리티에서 HTTP 세션을 어떻게 다룰지 결정 ㄴ세션을 어떻게 할지 설정 (세션을 생성하거나 사용하지 않는다.)
			.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			//URL 패턴별로 접근권한 규칙을 설정하는 부분.
			.authorizeHttpRequests(authorizeRequestConfigurer ->
				//특정 URL 패턴(.requestMatchers())을 로그인 유무나 
				//권한(.permitAll) 상관없이 누구나 접근할 수 있다.
					// "/" : 루트경로", "/auth/**" : /auth/로 시작하는 모든 하위 경로.
					
					authorizeRequestConfigurer.requestMatchers("/","/auth/**","/oauth2/**")
					//위에 선언된 url패턴 이외의 모든 요청은(.anyRequest())
					//인증된 사용자만(.authenticated()) 접근 가능하다.
					.permitAll().anyRequest().authenticated() 
			)
			.oauth2Login()
			//oauth2 로그인 설정
			.redirectionEndpoint()
			.baseUri("/oauth2/callback/*")
			.and()
			.authorizationEndpoint()
			.baseUri("/auth/authorize") //기본값이 아닌 /auth/authorize/github형태로 요청
			//http://localhost:5000/oauth2/callback/* 으로 들어오는 요청을
			//redirectionEndpoint에 설정된 곳으로 리다이렉트 하라는 뜻
			//아무 주소도 넣지 않았다면 baseUri인 'http://localhost:5000으로 리다이렉트 한다.'
			.and()
			.userInfoEndpoint() //OAuth2 인증이 성공한 후, 사용자 프로필 데이터를 가져오는 역
			.userService(oAuthUserServiceImpl) //사용자 정보를 처리하는 서비스를 지정.
			.and()
			.successHandler(oAuthSucessHandler)
			.and()
			.exceptionHandling()
			//403를 띄우는 명령어
			.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
			;
			
		

		//스프링 시큐리티 필터체인에 우리가 만든 필터를 삽입하는 위치를 지정하는 설정
			//jwtAuthenticationFilter가 UsernamePasswordAuthenticationalFilter 이전에 실행되도록 보장한다.
			//addFilterBefore : 2번째 인자를 실행하기전에 1번째 인자를 실행하겠다라는 뜻.
  	    http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		
		//UsernamePasswordAuthenticationFilter
		//스프링 시큐리티가 폼 기반 로그인을 처리하기 위해 제공하는 필터
		
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		//CORS 설정을 담기위한 객체의 생성.
		CorsConfiguration configuration = new CorsConfiguration();
		//허용할 출처(Origin) 지정.
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000",
				"http://app.springboot-kis.store/","https://app.springboot-kis.store/"));
		//허용할 메서드 지정.
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		//허용할 요청 헤더를 지정("*"는 모든 헤더를 허용하겠다는 의미)
		configuration.setAllowedHeaders(Arrays.asList("*"));
		//자격증명 허용 여부.
		//true로 설정을 해얗 브라우저가 요청 헤더를 함께 전송.
		configuration.setAllowCredentials(true);
		
		//URL 패턴별로 CORS 설정을 매핑할 소스 객체 생성
		UrlBasedCorsConfigurationSource source = 
				new UrlBasedCorsConfigurationSource();
		
		// "/**" 모든 경로 위에서 만든 configuration  적용
		source.registerCorsConfiguration("/**", configuration);
		
		//빈으로 등록된 CorsConfigurationSource를 반환
		return source;
	}

	
	
}//end class
