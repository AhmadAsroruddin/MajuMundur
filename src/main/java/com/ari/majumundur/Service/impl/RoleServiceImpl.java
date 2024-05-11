package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Constant.ERole;
import com.ari.majumundur.Models.Entities.Role;
import com.ari.majumundur.Repository.RoleRepository;
import com.ari.majumundur.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if(!optionalRole.isEmpty()){
            return optionalRole.get();
        }

        Role currRole = Role.builder()
                .name(role)
                .build();
        return roleRepository.saveAndFlush(currRole);

    }
}
