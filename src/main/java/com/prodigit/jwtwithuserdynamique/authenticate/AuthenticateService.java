package com.prodigit.jwtwithuserdynamique.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthenticateService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticateService.class);

    @Value("${jwt.expiration.min}")
    private int jwtExpirationInMinutes;

    @Value("${security.2fa.active}")
    private boolean doubleAuth;

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticateService(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto login(LoginDto loginDto) {
        log.info("Authentification [{}] en cours ....", loginDto.username());
        Instant instant = Instant.now();
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));

        String roles = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        String token = getToken(instant, jwtExpirationInMinutes, authenticate.getName(), roles);
        String refreshToken = getToken(instant, jwtExpirationInMinutes + 10, authenticate.getName(), roles);

        return new AuthResponseDto(token, refreshToken);
    }

    private String getToken(Instant instant, int jwtTimeExpire, String username, String roles) {

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(jwtTimeExpire, ChronoUnit.MINUTES))
                .subject(username)
                .claim("role", roles)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
                .from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );

        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
