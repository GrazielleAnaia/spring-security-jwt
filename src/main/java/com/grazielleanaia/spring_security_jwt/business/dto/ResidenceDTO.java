package com.grazielleanaia.spring_security_jwt.business.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class ResidenceDTO {

    private Long id;

    private String street;

    private String city;

    private String state;

    private String zipcode;
}
