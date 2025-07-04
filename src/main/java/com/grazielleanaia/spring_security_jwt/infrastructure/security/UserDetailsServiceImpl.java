package com.grazielleanaia.spring_security_jwt.infrastructure.security;


import com.grazielleanaia.spring_security_jwt.infrastructure.entity.Customer;
import com.grazielleanaia.spring_security_jwt.infrastructure.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repository to access customer data in the database
    @Autowired
    private CustomerRepository customerRepository;

    // Implement method to load customer details by email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
}
