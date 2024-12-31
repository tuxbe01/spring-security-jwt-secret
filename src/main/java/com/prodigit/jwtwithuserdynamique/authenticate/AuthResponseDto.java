package com.prodigit.jwtwithuserdynamique.authenticate;

public record AuthResponseDto(
        String token,
        String refreshToken
) {
}
