package com.user.basicusermanagement.model.enums;

import lombok.Getter;

@Getter
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    private String permissions;

    private Permission(String permission){
      this.permissions = permission;
    }

}
