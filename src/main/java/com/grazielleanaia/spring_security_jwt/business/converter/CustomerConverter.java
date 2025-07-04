package com.grazielleanaia.spring_security_jwt.business.converter;


import com.grazielleanaia.customer_registration.business.dto.CustomerDTO;
import com.grazielleanaia.customer_registration.business.dto.PhoneDTO;
import com.grazielleanaia.customer_registration.business.dto.ResidenceDTO;
import com.grazielleanaia.customer_registration.infrastructure.entity.Customer;
import com.grazielleanaia.customer_registration.infrastructure.entity.Phone;
import com.grazielleanaia.customer_registration.infrastructure.entity.Residence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerConverter {

    //Convert DTOs in entities

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .email(customerDTO.getEmail())
                .name(customerDTO.getName())
                .password(customerDTO.getPassword())
                .phones(toListPhone(customerDTO.getPhones()))
                .residences(convertToListResidence(customerDTO.getResidences()))
                .build();
    }

    public List<Phone> toListPhone(List<PhoneDTO> phoneDTO) {
        return phoneDTO.stream().map(this::convertPhone).toList();
    }

    public Phone convertPhone(PhoneDTO phoneDTO) {
        return Phone.builder()
                .code(phoneDTO.getCode())
                .number(phoneDTO.getNumber())
                .build();
    }

    public List<Residence> convertToListResidence(List<ResidenceDTO> residenceDTO) {
        return residenceDTO.stream().map(this::convertResidence).toList();
    }

    public Residence convertResidence(ResidenceDTO residenceDTO) {
        return Residence.builder()
                .street(residenceDTO.getStreet())
                .city(residenceDTO.getCity())
                .state(residenceDTO.getState())
                .zipcode(residenceDTO.getZipcode())
                .build();
    }

    //Convert entities in DTOs

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .residences(convertToListResidenceDTO(customer.getResidences()))
                .phones(convertToListPhoneDTO(customer.getPhones()))
                .build();
    }


    public List<PhoneDTO> convertToListPhoneDTO(List<Phone> phone) {
        return phone.stream().map(this::convertToPhoneDTO).toList();
    }

    public PhoneDTO convertToPhoneDTO(Phone phone) {
        return PhoneDTO.builder()
                .number(phone.getNumber())
                .code(phone.getCode())
                .build();
    }


    public List<ResidenceDTO> convertToListResidenceDTO(List<Residence> residence) {
        return residence.stream().map(this::convertToResidenceDTO).toList();
    }


    public ResidenceDTO convertToResidenceDTO(Residence residence) {
        return ResidenceDTO.builder()
                .street(residence.getStreet())
                .city(residence.getCity())
                .state(residence.getState())
                .zipcode(residence.getZipcode())
                .build();
    }

    //Update methods

    public Customer updateCustomer(CustomerDTO customerDTO, Customer customer) {
        return Customer.builder()
                .id(customer.getId())
                .name(customerDTO.getName() != null ? customerDTO.getName() : customer.getName())
                .email(customerDTO.getEmail() != null ? customerDTO.getEmail() : customer.getEmail())
                .password(customerDTO.getPassword() != null ? customerDTO.getPassword() : customer.getPassword())
                .build();

    }

    public Phone updatePhone(PhoneDTO phoneDTO, Phone phone) {
        return Phone.builder()
                .id(phone.getId())
                .number(phoneDTO.getNumber() != null ? phoneDTO.getNumber() : phone.getNumber())
                .code(phoneDTO.getCode() != null ? phoneDTO.getCode() : phone.getCode())
                .build();
    }

    public Residence updateResidence(ResidenceDTO residenceDTO, Residence residence) {
        return Residence.builder()
                .id(residence.getId())
                .street(residenceDTO.getStreet() != null ? residenceDTO.getStreet() : residence.getStreet())
                .city(residenceDTO.getCity() != null ? residenceDTO.getCity() : residence.getCity())
                .state(residenceDTO.getState() != null ? residenceDTO.getState() : residence.getState())
                .zipcode(residenceDTO.getZipcode() != null ? residenceDTO.getZipcode() : residence.getZipcode())
                .build();
    }

    //Post methods to include residence and phone to an existent customer

    public Phone includePhone(PhoneDTO phoneDTO, Long customerId) {
        return Phone.builder()
                .customer_id(customerId)
                .number(phoneDTO.getNumber())
                .code(phoneDTO.getCode())
                .build();
    }

    public Residence includeResidence(ResidenceDTO residenceDTO, Long customerId) {
        return Residence.builder()
                .customer_id(customerId)
                .street(residenceDTO.getStreet())
                .city(residenceDTO.getCity())
                .state(residenceDTO.getState())
                .zipcode(residenceDTO.getZipcode())
                .build();
    }


}