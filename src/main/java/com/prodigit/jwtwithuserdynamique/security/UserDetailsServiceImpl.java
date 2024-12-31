package com.prodigit.jwtwithuserdynamique.security;

import com.prodigit.jwtwithuserdynamique.utilisateurs.Utilisateur;
import com.prodigit.jwtwithuserdynamique.utilisateurs.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;

    public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Chargement de l'utilisateur : {}", username);
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);
        Utilisateur utilisateur1 = utilisateur.orElseThrow(() -> new RuntimeException("Username et/ou mot de passe incorrect"));
        return new User(utilisateur1.getUsername(), utilisateur1.getPassword(),
                utilisateur1.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
    }
}
