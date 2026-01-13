package backend.capstone.domain.user.mapper;

import backend.capstone.auth.util.OAuthAttributes;
import backend.capstone.domain.user.entity.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserMapper {

	public static User toEntity(OAuthAttributes oAuthAttributes) {
		return User.builder()
			.provider(oAuthAttributes.getProvider())
			.providerId(oAuthAttributes.getProviderId())
			.nickname(oAuthAttributes.getNickname())
			.profileImageUrl(oAuthAttributes.getProfileImageUrl())
			.build();
	}

}
