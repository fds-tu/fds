package bg.tusofia.fcst.ksi.practikum.fds.controllers.auth;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.authentication.CreateAccessRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.authentication.GetAccessRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.authentication.TokenPair;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.entities.SuccessResponseEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.services.ipv4.Ipv4AddressService;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${security.authentication.jwt.token.access.expiration}")
    private String accessTokenExpiration;

    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final Ipv4AddressService ipv4AddressService;

    @GetMapping
    public ResponseEntity<?> login(@RequestBody @Valid GetAccessRequest request, HttpServletRequest servletRequest, HttpServletResponse response)  {
        return generateResponseEntity(authService.login(request.getUsername(), request.getPassword(), ipv4AddressService.extractClientIp(servletRequest)));
    }

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody @Valid CreateAccessRequest request, HttpServletRequest servletRequest, HttpServletResponse response) throws Exception {
        return generateResponseEntity(authService.signup(modelMapper.map(request, User.class), ipv4AddressService.extractClientIp(servletRequest)));
    }

    @PatchMapping
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader, HttpServletResponse response) {
        String refreshToken = authHeader.replace("Bearer ", "");

        return generateResponseEntity(authService.refresh(refreshToken));
    }

    @DeleteMapping
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> generateResponseEntity(TokenPair tokenPair) {
        ResponseCookie cookie = ResponseCookie
                .from("access-token", tokenPair.getAccessToken())
                .httpOnly(true)
                .path("/")
                .secure(true)
                .maxAge(Long.parseLong(accessTokenExpiration))
                .sameSite("Strict")
                .build();

        return new SuccessResponseEntity<>(Map.of("refreshToken", tokenPair.getRefreshToken()), MultiValueMap.fromSingleValue(Map.of("Set-Cookie", cookie.toString())), HttpStatus.OK);
    }
}
