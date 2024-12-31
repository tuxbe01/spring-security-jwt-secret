package com.prodigit.jwtwithuserdynamique.utilisateurs;

public record UtilisateurDto(
         String username,
         String password,
         String email,
         boolean firstConnection,
         int attemptPassword,
         boolean locked,
         boolean passwordExpired,
         String roles
) {
}
