package com.palombetas.api.gerartimes.domain.entity;

import com.palombetas.api.gerartimes.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    public UserEntity(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }
    public UserEntity(String login, String senha) {
        this.login = login;
        this.password = senha;
        this.userRole = UserRole.DIRECTOR;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_DIRECTOR")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_DIRECTOR"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

}
