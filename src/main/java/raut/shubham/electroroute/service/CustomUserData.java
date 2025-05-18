package raut.shubham.electroroute.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import raut.shubham.electroroute.entity.UserInfo;
import raut.shubham.electroroute.entity.UserRoles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserData extends UserInfo implements UserDetails {
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserData(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(UserRoles role : userInfo.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }
}
