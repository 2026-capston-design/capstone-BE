package backend.capstone.auth.jwt;

import java.security.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final JwtProperties props;
	private final Key key;


}
