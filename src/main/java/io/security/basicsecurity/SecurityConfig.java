package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
		;
	}
}
