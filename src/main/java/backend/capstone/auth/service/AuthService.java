package backend.capstone.auth.service;

import backend.capstone.auth.dto.LoginResponse;
import backend.capstone.auth.service.client.KakaoApiClient;
import backend.capstone.auth.service.dto.KakaoUserInfoResponse;
import backend.capstone.domain.user.entity.ProviderType;
import backend.capstone.domain.user.entity.User;
import backend.capstone.domain.user.mapper.UserMapper;
import backend.capstone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final KakaoApiClient kakaoApiClient;

	@Transactional
	public LoginResponse kakaoLogin(String kakaoAccessToken) {
		KakaoUserInfoResponse kakaoUser = kakaoApiClient.getUserInfo(kakaoAccessToken);
		User user = userRepository.findByProviderAndProviderId(ProviderType.KAKAO, kakaoUser.id())
			.map(existing -> {
//				existing.updateProfile(oa.getNickname(), oa.getProfileImageUrl()); //TODO: 프로필 업데이트 기능 구현
				return existing;
			})
			.orElseGet(() -> userRepository.save(
				UserMapper.toEntity(kakaoUser))
			); //TODO: userService로 이동

		return new LoginResponse(user.getId(), user.getNickname(), user.getProfileImageUrl());
	}


}
