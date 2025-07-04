package com.grazielleanaia.spring_security_jwt.controller;


import com.grazielleanaia.customer_registration.business.CustomerService;
import com.grazielleanaia.customer_registration.business.dto.CustomerDTO;
import com.grazielleanaia.customer_registration.business.dto.PhoneDTO;
import com.grazielleanaia.customer_registration.business.dto.ResidenceDTO;
import com.grazielleanaia.customer_registration.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerDTO customerDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerDTO.getEmail(), customerDTO.getPassword()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> findCustomerByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(customerService.findCustomerByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable String email) {
        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/customer")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.updateCustomer(token, customerDTO));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO,
                                                @RequestParam("id") Long idPhone) {
        return ResponseEntity.ok(customerService.updatePhone(idPhone, phoneDTO));
    }

    @PutMapping("/residence")
    public ResponseEntity<ResidenceDTO> updateResidence(@RequestBody ResidenceDTO residenceDTO,
                                                        @RequestParam("id") Long idResidence) {
        return ResponseEntity.ok(customerService.updateResidence(idResidence, residenceDTO));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> includePhone(@RequestBody PhoneDTO phoneDTO,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.includePhone(phoneDTO, token));
    }

    @PostMapping("/residence")
    public ResponseEntity<ResidenceDTO> includeResidence(@RequestBody ResidenceDTO residenceDTO,
                                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.includeResidence(residenceDTO, token));
    }
}
