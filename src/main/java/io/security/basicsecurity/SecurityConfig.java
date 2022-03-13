package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity // 여러 클래스를 import 하여 실행시키는 annotation. 이걸 명시해줘야 웹 보안이 활성화된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * 인가 정책
		 */
		http
				.authorizeRequests()
				.anyRequest().authenticated(); // 어떠한 요청에도.인증을 받아라  라는 뜻의 코드

		/**
		 * 인증 정책
		 */
		http
				.formLogin() // 기본적으로 form-login 방식으로 인증을 할 수 있도록 api 를 설정.
//				.loginPage("/loginPage") // root path 에 접근하려 할 때 무조건 이 페이지로 이동된다.
				.defaultSuccessUrl("/") // login 성공하게 될 경우 연결되는 path : handler 가 없으면 동작하는 것 볼 수 있다.
				.failureUrl("/login") // login 실패하게 될 경우 연결되는 path : handler 가 없으면 동작하는 것 볼 수 있다.
				.usernameParameter("userId") // default : username
				.passwordParameter("pw") // default : password
				.loginProcessingUrl("/login_proc") // 로그인 form action URL. action="/login_proc"
				.successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 후 처리되는 handler
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						System.out.println(authentication.getName()); // user 가 출력됨
						response.sendRedirect("/");
					}
				})
				.failureHandler(new AuthenticationFailureHandler() { // 로그인 실패 후 처리되는 handler
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
						System.out.println("exception " + exception.getMessage()); // exception 자격 증명에 실패하였습니다. 출력됨.
						response.sendRedirect("/login");
					}
				}).permitAll() // login page 는 인증이 없어도 들어갈 수 있어야 하기 때문에 => 인가 정책에서 '어떠한 요청' 에도 인증을 필수적으로 받게끔 설정했기 때문
		;
	}
}
