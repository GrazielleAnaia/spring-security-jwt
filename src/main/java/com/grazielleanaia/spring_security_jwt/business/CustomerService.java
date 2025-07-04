package com.grazielleanaia.spring_security_jwt.business;

import com.grazielleanaia.customer_registration.business.converter.CustomerConverter;
import com.grazielleanaia.customer_registration.business.dto.CustomerDTO;
import com.grazielleanaia.customer_registration.business.dto.PhoneDTO;
import com.grazielleanaia.customer_registration.business.dto.ResidenceDTO;
import com.grazielleanaia.customer_registration.infrastructure.entity.Customer;
import com.grazielleanaia.customer_registration.infrastructure.entity.Phone;
import com.grazielleanaia.customer_registration.infrastructure.entity.Residence;
import com.grazielleanaia.customer_registration.infrastructure.exception.ConflictException;
import com.grazielleanaia.customer_registration.infrastructure.exception.ResourceNotFoundException;
import com.grazielleanaia.customer_registration.infrastructure.repository.CustomerRepository;
import com.grazielleanaia.customer_registration.infrastructure.repository.PhoneRepository;
import com.grazielleanaia.customer_registration.infrastructure.repository.ResidenceRepository;
import com.grazielleanaia.customer_registration.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerConverter customerConverter;
    private final JwtUtil jwtUtil;
    private final PhoneRepository phoneRepository;
    private final ResidenceRepository residenceRepository;


    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        emailExists(customerDTO.getEmail());
        customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        Customer customer = customerConverter.convertToCustomer(customerDTO);
        Customer customer1 = customerRepository.save(customer);
        return customerConverter.convertToCustomerDTO(customer1);
    }

    public void emailExists(String email) {
        try {
            boolean exist = verifyEmail(email);
            if (exist) {
                throw new ConflictException("Email is already registered." + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email is already registered.", e.getCause());
        }
    }

    boolean verifyEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public CustomerDTO findCustomerByEmail(String email) {
        try {
            Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("Email not found." + email));
            return customerConverter.convertToCustomerDTO(customer);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Customer not found.");
        }
    }

    public void deleteCustomerByEmail(String email) {
        try {
            customerRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email not found" + email));
            customerRepository.deleteByEmail(email);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Customer not found", e.getCause());
        }
    }

    //Update methods

    public CustomerDTO updateCustomer(String token, CustomerDTO customerDTO) {
        String email = jwtUtil.extractUsername(token);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found." + email));

        Customer customer1 = customerConverter.updateCustomer(customerDTO, customer);
        return customerConverter.convertToCustomerDTO(customerRepository.save(customer1));
    }

    public PhoneDTO updatePhone(Long idPhone, PhoneDTO phoneDTO) {
        Phone phone = phoneRepository.findById(idPhone).orElseThrow(() ->
                new ResourceNotFoundException("id not found"));
        Phone phone1 = customerConverter.updatePhone(phoneDTO, phone);
        Phone phone2 = phoneRepository.save(phone1);
        return customerConverter.convertToPhoneDTO(phone2);
    }

    public ResidenceDTO updateResidence(Long idResidence, ResidenceDTO residenceDTO) {
        Residence residence = residenceRepository.findById(idResidence).orElseThrow(() ->
                new ResourceNotFoundException("Id not found." + idResidence));
        Residence residence1 = customerConverter.updateResidence(residenceDTO, residence);
        return customerConverter.convertToResidenceDTO(residenceRepository.save(residence1));
    }

    //Methods to include Phone and Residence to existing customer

    public PhoneDTO includePhone(PhoneDTO phoneDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found." + email));
        Phone phone = customerConverter.includePhone(phoneDTO, customer.getId());
        Phone phone1 = phoneRepository.save(phone);
        return customerConverter.convertToPhoneDTO(phone1);
    }

    public ResidenceDTO includeResidence(ResidenceDTO residenceDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found." + email));
        Residence residence = customerConverter.includeResidence(residenceDTO, customer.getId());
        return customerConverter.convertToResidenceDTO(residenceRepository.save(residence));
    }


}
