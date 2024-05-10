package org.saa.myrokomary_class20.dto;

import org.saa.myrokomary_class20.entity.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrinciple implements UserDetails {

    private AccountEntity accountEntity;

    public UserPrinciple(AccountEntity accountEntity){
        this.accountEntity = accountEntity;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.accountEntity.getRoles();
    }

    @Override
    public String getPassword() {
        return this.accountEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.accountEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountEntity.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountEntity.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountEntity.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.accountEntity.isEnabled();
    }
}
