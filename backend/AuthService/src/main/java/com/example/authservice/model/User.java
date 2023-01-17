package com.example.authservice.model;

import com.example.authservice.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.AuthProvider;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String phoneNumber;
    private Boolean deleted=false;
    @OneToOne
    private UserAuth userAuth;
    private UserRole role;
    private Boolean blocked;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserAuth().getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getUserAuth().getIsEnabled();
    }
}
