package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Constant.ERole;
import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Entities.Merchant;
import com.ari.majumundur.Models.Entities.Role;
import com.ari.majumundur.Models.Entities.UserCredential;
import com.ari.majumundur.Models.Request.AuthRequestCustomer;
import com.ari.majumundur.Models.Request.AuthRequestMerchant;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.MerchantResponse;
import com.ari.majumundur.Repository.CustomerRepository;
import com.ari.majumundur.Repository.MerchantRepository;
import com.ari.majumundur.Repository.UserCredentialRepository;
import com.ari.majumundur.Service.AuthService;
import com.ari.majumundur.Service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleService roleService;
    private final UserCredentialRepository userCredentialRepository;
    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;
    @Override
    @Transactional
    public CustomerResponse createCustomer(AuthRequestCustomer customerRequest) {
        Role newRole =roleService.getOrSave(ERole.CUSTOMER);

        UserCredential userCredential = UserCredential.builder()
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .role(newRole)
                .build();
        userCredentialRepository.saveAndFlush(userCredential);

        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .points(0)
                .mobilePhone(customerRequest.getMobilePhone())
                .address(customerRequest.getAddress())
                .userCredential(userCredential)
                .build();
        customerRepository.save(customer);

        return CustomerResponse.builder()
                .username(userCredential.getUsername())
                .name(customer.getName())
                .address(customer.getAddress())
                .mobile_phone(customer.getMobilePhone())
                .points(customer.getPoints())
                .email(customer.getEmail())
                .build();
    }

    @Override
    @Transactional
    public MerchantResponse createMerchant(AuthRequestMerchant merchantRequest) {
        Role newRole =roleService.getOrSave(ERole.CUSTOMER);

        UserCredential userCredential = UserCredential.builder()
                .username(merchantRequest.getUsername())
                .password(merchantRequest.getPassword())
                .role(newRole)
                .build();
        userCredentialRepository.saveAndFlush(userCredential);

        Merchant merchant = Merchant.builder()
                .name(merchantRequest.getMerchantName())
                .email(merchantRequest.getEmail())
                .mobilePhone(merchantRequest.getMobile_phone())
                .address(merchantRequest.getAddress())
                .userCredential(userCredential)
                .build();
        return MerchantResponse.builder()
                .username(userCredential.getUsername())
                .merchantName(merchant.getName())
                .email(merchant.getEmail())
                .mobilePhone(merchant.getMobilePhone())
                .address(merchant.getAddress())
                .build();
    }
}
