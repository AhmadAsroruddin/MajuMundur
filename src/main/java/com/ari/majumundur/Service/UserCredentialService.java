package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserCredentialService extends UserDetailsService {
    AppUser loadUserByUserId(String id);

}
