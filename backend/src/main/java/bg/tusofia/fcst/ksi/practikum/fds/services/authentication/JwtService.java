package bg.tusofia.fcst.ksi.practikum.fds.services.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.RefreshToken;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.Session;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.RefreshTokenInvalidException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.auth.RefreshTokenRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.auth.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {


    @Value("${security.authentication.jwt.token.secret}")
    private String SECRET;

    @Value("${security.authentication.jwt.token.access.expiration}")
    private String ACCESS_TOKEN_EXPIRATION_SECONDS;

    @Value("${security.authentication.jwt.token.refresh.expiration}")
    private String REFRESH_TOKEN_EXPIRATION_SECONDS;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    private final SessionRepository sessionRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    public String generateAccessToken(Long userId, Long sessionId, Long refreshTokenId) {
        return Jwts.builder()
                .claims(Map.of(
                        "user_id", userId,
                        "session_id", sessionId,
                        "refresh_token_id", refreshTokenId
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(Long.parseLong(ACCESS_TOKEN_EXPIRATION_SECONDS))))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(Long sessionId, Long refreshTokenId) {
        return Jwts.builder()
                .claims(Map.of(
                        "session_id", sessionId,
                        "token_id", refreshTokenId
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(Long.parseLong(REFRESH_TOKEN_EXPIRATION_SECONDS))))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch (Exception e) {
            throw new InvalidResourceException("Token");
        }
    }

    public Claims extractAllClaims(HttpServletRequest request) {
        return this.extractAllClaims(this.getAccessToken(request));
    }

    public Session getSessionAndCheckToken(Long sessionId, Long tokenId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "id", sessionId.toString()));

        sessionRepository.save(session);

        RefreshToken refreshToken = refreshTokenRepository.findById(tokenId).orElseThrow(() -> new ResourceNotFoundException("RefreshToken", "id", tokenId.toString()));

        if (refreshToken.getIsUsed()) {
            sessionRepository.deleteById(sessionId);
            throw new RefreshTokenInvalidException();
        }

        return session;
    }

    public Cookie getCookie(HttpServletRequest request, String cookieName, String resourceName) {
        final Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0)  {
            throw new InvalidResourceException("Token Cookie");
        }

        return Arrays.stream(cookies).filter(c -> c.getName().equals("access-token")).findAny().orElseThrow(() -> new InvalidResourceException("Token Cookie"));
    }

    public Cookie getTokenCookie(HttpServletRequest request) {
        return getCookie(request, "access-token", "Token Cookie");
    }

    public String getAccessToken(HttpServletRequest request) {
        return getTokenCookie(request).getValue();
    }

    public Session getCurrentSessionOrCreate(User user, String ipAddress) {
        Session session = sessionRepository.findByUserIdAndIpv4Address(user.getId(), ipAddress).orElse(null);
        if (session == null) {
            session = new Session();
            session.setUser(user);
            session.setIpv4Address(ipAddress);
            sessionRepository.save(session);
        }

        return session;
    }
}