package com.chatapp.backend.model;

import java.util.HashSet;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
  private AppUser user;

  public SecurityUser(AppUser user) {
    this.user = user;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public boolean isEnabled() {
    return user.getEnabled();
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
  public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
    return new HashSet<>();
  }
}
