package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.AppUser;
import com.ari.majumundur.Models.Entities.UserCredential;
import com.ari.majumundur.Repository.UserCredentialRepository;
import com.ari.majumundur.Service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialServiceImpl implements UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    @Override
    public AppUser loadUserByUserId(String userId) {
        UserCredential userCredential = userCredentialRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("USER NOT FOUND"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserCredential userCredential = userCredentialRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("USER NOT FOUND"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}
