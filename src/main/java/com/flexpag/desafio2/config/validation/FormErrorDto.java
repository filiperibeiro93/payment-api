package com.flexpag.desafio2.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormErrorDto {

    private String field;
    private String error;

}
