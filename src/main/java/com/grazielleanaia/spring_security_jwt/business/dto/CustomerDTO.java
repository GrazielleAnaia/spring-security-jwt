package com.grazielleanaia.spring_security_jwt.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class CustomerDTO {

    private String name;

    private String password;

    private String email;

    private List<PhoneDTO> phones;

    private List<ResidenceDTO>residences;
}
