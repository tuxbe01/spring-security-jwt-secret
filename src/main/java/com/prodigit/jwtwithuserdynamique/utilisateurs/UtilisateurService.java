package com.prodigit.jwtwithuserdynamique.utilisateurs;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService implements UtilisateurDao {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto create(CreateUtilisateur utilisateur) {
        Optional<Utilisateur> byUsername = utilisateurRepository.findByUsername(utilisateur.username());
        if (byUsername.isPresent())
            throw new RuntimeException("Utilisateur " + utilisateur.username() + " existe déjà !");
        return null;
    }
}
