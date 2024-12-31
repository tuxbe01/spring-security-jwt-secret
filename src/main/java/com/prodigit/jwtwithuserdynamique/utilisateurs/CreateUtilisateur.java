package com.prodigit.jwtwithuserdynamique.utilisateurs;

public record CreateUtilisateur(
        String username,
        String password,
        String email
) {
}
