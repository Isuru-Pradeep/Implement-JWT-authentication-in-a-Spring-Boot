package com.paymedia.jwt_authentication.repository;

import com.paymedia.jwt_authentication.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(String role);
}
