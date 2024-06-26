package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByUsername(String username);
}
