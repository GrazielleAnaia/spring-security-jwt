package com.grazielleanaia.spring_security_jwt.business.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class PhoneDTO {

    private Long id;

    private String number;

    private String code;
}
