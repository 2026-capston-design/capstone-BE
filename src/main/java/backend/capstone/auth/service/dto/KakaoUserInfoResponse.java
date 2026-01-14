package backend.capstone.auth.service.dto;

public record KakaoUserInfoResponse(
	Long id,
	KakaoAccount kakao_account
) {

	public record KakaoAccount(
		Profile profile
	) {

	}

	public record Profile(
		String nickname,
		String profile_image_url
	) {

	}

}
