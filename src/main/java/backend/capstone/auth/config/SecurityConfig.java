package backend.capstone.auth.config;

import backend.capstone.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
			// API 테스트 편하게 (추후 단계에서 상황 맞춰 조정)
			.csrf(csrf -> csrf.disable())

			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
				.anyRequest().authenticated()
			)

			.oauth2Login(oauth2 -> oauth2
				.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
				.defaultSuccessUrl("/", true)
			)

			// (선택) 기본 로그인 폼은 끄고 싶으면 주석 해제
			// .formLogin(form -> form.disable())

			// 기본 설정
			.httpBasic(Customizer.withDefaults());

		return http.build();
	}


}
