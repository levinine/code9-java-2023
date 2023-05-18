package com.code9.beershop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerOrderDto {

    private Long id;

    private String name;

    private String street;

    private String city;

    private String zip;

    @CreditCardNumber(message="Not a valid credit card number.")
    private String creditCardNumber;

    private String creditCardExpiration;

    private String creditCardSecurityCode;

    @NotEmpty
    private List<BeerDto> beersDto;
}
