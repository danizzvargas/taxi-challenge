package com.gbm.challenge.taxitracking.security;

import com.gbm.challenge.taxitracking.entity.TaxiUser;
import com.gbm.challenge.taxitracking.repository.TaxiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom user management.
 *
 * @author danizz
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TaxiUserRepository taxiUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Trying to access with username: {}", username);

        TaxiUser taxiUser = taxiUserRepository.findByPhone(username);

        if (taxiUser != null) {
            return User.withUsername(username)
                    .password(taxiUser.getPassword())
                    .roles("USER")
                    .build();
        } else {
            log.warn("Username '{}' not found.", username);
            throw new UsernameNotFoundException("User not found.");
        }
    }

}
