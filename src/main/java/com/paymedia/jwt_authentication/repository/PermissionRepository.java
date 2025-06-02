package com.paymedia.jwt_authentication.repository;

import com.paymedia.jwt_authentication.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository  extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}
