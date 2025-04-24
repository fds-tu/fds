package bg.tusofia.fcst.ksi.practikum.fds.services.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.authentication.TokenPair;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.RefreshToken;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.Session;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.RefreshTokenRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.SessionRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenPair signup(User user, String ipv4Address) {
        user.encodePassword(passwordEncoder::encode);
        userRepository.save(user);

        return createSessionAndTokens(user, ipv4Address);
    }

    public TokenPair login(String username, String password, String ipv4Address) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidResourceException("Credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidResourceException("Credentials");
        }

        return createSessionAndTokens(user, ipv4Address);
    }

    public TokenPair refresh(String refreshTokenRaw) {
        Claims claims = jwtService.extractAllClaims(refreshTokenRaw);
        Long sessionId = claims.get("session_id", Long.class);
        Long tokenId = claims.get("token_id", Long.class);

        Session session = jwtService.getSessionAndCheckToken(sessionId, tokenId);

        RefreshToken oldToken = refreshTokenRepository.findById(tokenId).orElseThrow(() -> new ResourceNotFoundException("Token", "id", tokenId.toString()));
        oldToken.setIsUsed(Boolean.TRUE);

        sessionRepository.save(session);
        refreshTokenRepository.save(oldToken);

        return rotateTokens(session.getUser(), session);
    }

    public void logout(HttpServletRequest request) {
        Claims claims = jwtService.extractAllClaims(request);
        Long sessionId = claims.get("session_id", Long.class);

        sessionRepository.deleteById(sessionId);
    }

    private TokenPair createSessionAndTokens(User user, String ipAddress) {
        Session session = jwtService.getCurrentSessionOrCreate(user, ipAddress);

        return rotateTokens(user, session);
    }

    private TokenPair rotateTokens(User user, Session session) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setSession(session);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtService.generateAccessToken(user.getId(), session.getId(), refreshToken.getId());
        String refreshTokenJwt = jwtService.generateRefreshToken(session.getId(), refreshToken.getId());

        return new TokenPair(accessToken, refreshTokenJwt);
    }
}
