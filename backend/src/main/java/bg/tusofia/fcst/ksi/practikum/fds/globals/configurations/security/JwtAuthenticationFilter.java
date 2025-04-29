package bg.tusofia.fcst.ksi.practikum.fds.globals.configurations.security;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.SessionRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.user.UserJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserJpaRepository userJpaRepository;
    private final SessionRepository sessionRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserJpaRepository userJpaRepository, SessionRepository sessionRepository) {
        this.jwtService = jwtService;
        this.userJpaRepository = userJpaRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("auth") && !request.getMethod().equals("DELETE")) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtService.extractAllClaims(request);

        Long userId = claims.get("user_id", Long.class);
        Long sessionId = claims.get("session_id", Long.class);
        Long refreshTokenId = claims.get("refresh_token_id", Long.class);

        User user = userJpaRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        jwtService.getSessionAndCheckToken(sessionId, refreshTokenId);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                Collections.emptyList()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}