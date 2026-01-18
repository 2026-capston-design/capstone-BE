package backend.capstone.auth.util;

import backend.capstone.domain.user.entity.ProviderType;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthAttributes {

	private ProviderType provider;
	private String providerId; //provider가 제공해준 사용자의 id
	private String nickname;
	private String profileImageUrl;

	public static OAuthAttributes ofKakao(Map<String, Object> attributes) {
		String providerId = String.valueOf(attributes.get("id"));
		Map<String, Object> properties = transferMap(attributes.get("properties"));

		String nickname = (String) properties.get("nickname");
		String profileImage = (String) properties.get("profile_image");

		return new OAuthAttributes(ProviderType.KAKAO, providerId, nickname, profileImage);
	}

	private static Map<String, Object> transferMap(Object o) {
		return (o instanceof Map) ? (Map<String, Object>) o : Map.of();
	}


}
