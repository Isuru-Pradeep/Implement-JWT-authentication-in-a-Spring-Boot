package com.paymedia.jwt_authentication.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.info("PermissionEvaluator hasPermission");
        log.info("targetDomainObject: " + targetDomainObject);
        log.info("permission: " + permission);
        log.info("authentication: " + authentication);
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
//        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPrivilege(authentication, targetDomainObject.toString(), permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        log.info("PermissionEvaluator hasPermission");
        log.info("targetId: " + targetId);
        log.info("targetType: " + targetType);
        log.info("permission: " + permission);
        log.info("authentication: " + authentication);
        if ((authentication == null) || (targetId == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication authentication, String targetType, String permission) {
        log.info(authentication.getName() + " has privilege " + permission);
        for(GrantedAuthority authority : authentication.getAuthorities()) {
            if(authority.getAuthority().startsWith(targetType)&& authority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }
}
