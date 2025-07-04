package com.grazielleanaia.spring_security_jwt.infrastructure.security;


import com.grazielleanaia.customer_registration.infrastructure.entity.Customer;
import com.grazielleanaia.customer_registration.infrastructure.repository.CustomerRepository;
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
        // Busca o usuário no banco de dados pelo e-mail
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getEmail()) // Define o nome de usuário como o e-mail
                .password(customer.getPassword()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
