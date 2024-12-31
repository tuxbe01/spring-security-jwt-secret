package com.prodigit.jwtwithuserdynamique.utilisateurs;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String email;
    private boolean firstConnection = true;
    private int attemptPassword = 0;
    private boolean locked = false;
    private boolean passwordExpired = false;
    @OneToMany(mappedBy = "utilisateur")
    List<Roles> roles = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(List<Roles> roles, boolean passwordExpired, boolean locked, int attemptPassword, boolean firstConnection, String email, String password, String username) {
        this.roles = roles;
        this.passwordExpired = passwordExpired;
        this.locked = locked;
        this.attemptPassword = attemptPassword;
        this.firstConnection = firstConnection;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(boolean firstConnection) {
        this.firstConnection = firstConnection;
    }

    public int getAttemptPassword() {
        return attemptPassword;
    }

    public void setAttemptPassword(int attemptPassword) {
        this.attemptPassword = attemptPassword;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstConnection=" + firstConnection +
                ", attemptPassword=" + attemptPassword +
                ", locked=" + locked +
                ", passwordExpired=" + passwordExpired +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return firstConnection == that.firstConnection && attemptPassword == that.attemptPassword && locked == that.locked && passwordExpired == that.passwordExpired && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, firstConnection, attemptPassword, locked, passwordExpired, roles);
    }
}
