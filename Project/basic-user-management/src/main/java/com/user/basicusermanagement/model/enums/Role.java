package com.user.basicusermanagement.model.enums;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

import static com.user.basicusermanagement.model.enums.Permission.*;

@Getter
public enum Role {
  USER(Collections.emptySet()),
  ADMIN(Set.of(ADMIN_READ,
               ADMIN_UPDATE,
               ADMIN_DELETE,
               ADMIN_CREATE));

  private final Set<Permission> permissions;

  private Role(Set<Permission> permission){
        this.permissions = permission;
  }

  public List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.name()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
