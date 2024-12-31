package com.prodigit.jwtwithuserdynamique.utilisateurs;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ROLES")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    Utilisateur utilisateur;

    public Roles() {
    }

    public Roles(String name, Utilisateur utilisateur) {
        this.name = name;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", utilisateur=" + utilisateur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(id, roles.id) && Objects.equals(name, roles.name) && Objects.equals(utilisateur, roles.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, utilisateur);
    }
}
