package com.ari.majumundur.Repository;

import com.ari.majumundur.Constant.ERole;
import com.ari.majumundur.Models.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);

}
