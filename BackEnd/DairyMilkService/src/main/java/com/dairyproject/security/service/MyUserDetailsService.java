package com.dairyproject.security.service;

import com.dairyproject.entities.Administrator;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.repositories.AdministratorRepository;
import com.dairyproject.repositories.ConsumerRepository;
import com.dairyproject.repositories.SellerRepository;
import com.dairyproject.security.model.UserPrincipal;
import com.dairyproject.security.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final AdministratorRepository administratorRepository;
    private final ConsumerRepository consumerRepository;
    private final SellerRepository sellerRepository;

    public MyUserDetailsService(AdministratorRepository administratorRepository, ConsumerRepository consumerRepository, SellerRepository sellerRepository) {
        this.administratorRepository = administratorRepository;
        this.consumerRepository = consumerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = null;
        user = isAdmin(username);
        if(isNull(user)){
            user = isSeller(username);
        }
        if(isNull(user)){
            user = isConsumer(username);
        }
        if (isNull(user)) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }

    private Users isConsumer(String emailId) {
        Optional<ConsumerDetails> user =  this.consumerRepository.findByEmailId(emailId);
        if(user.isPresent()){
            ConsumerDetails consumerDetails = user.get();
            return Users.builder().username(consumerDetails.getEmailId())
                    .password(consumerDetails.getPassword())
                    .role("CONSUMER")
                    .build();
        }
        return null;
    }

    private Users isSeller(String emailId) {
        Optional<SellerDetails> user = this.sellerRepository.findByEmailId(emailId);
        if(user.isPresent()){
            SellerDetails sellerDetails = user.get();
            return Users.builder().username(sellerDetails.getEmailId())
                    .password(sellerDetails.getPassword())
                    .role("SELLER")
                    .build();
        }
        return null;
    }


    private Users isAdmin(String emailId) {
        Optional<Administrator> user = this.administratorRepository.findByEmailId(emailId);
        if(user.isPresent()){
            Administrator administrator = user.get();
            return Users.builder().username(administrator.getEmailId())
                                    .password(administrator.getPassword())
                    .role("ADMIN")
                                    .build();
        }
        return null;
    }
}
