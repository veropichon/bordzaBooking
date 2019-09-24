package com.bordza.booking.bordzaBooking.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity (name = "user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usrId;

    @Email
    @Column(nullable = false, length = 50, unique = true)
    private String usrEmail;

    @Column(nullable = false, length = 60)
    private String usrPwd;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false)
    private Boolean usrValidated;

    /**
     * Display Default values
     * @return user entity with default role
     */
    public static UserEntity defaultValue(UserEntity userEntity) {

        if(userEntity.role == null || userEntity.role.isEmpty()){
            userEntity.role = "CLIENT";
        }

        if (userEntity.usrValidated == null) {
            userEntity.usrValidated = true;
        }

        return userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_" + role));

        return list;
    }

    @Override
    public String getPassword() {
        return usrPwd;
    }

    @Override
    public String getUsername() {
        return usrEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
