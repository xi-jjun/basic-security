package io.security.basicsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	/**
	 * 정상적으로 서버에 접근할 수 있지만 '누구라도' 접근이 가능하다.
	 * => 보완에 취약한 구조.
	 *
	 * 현재 시스템을 보완이 적용된 시스템으로 작업을 할 것이다. => SecurityConfig 로 사용자 정의 보안 class 생성
	 * @return
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
