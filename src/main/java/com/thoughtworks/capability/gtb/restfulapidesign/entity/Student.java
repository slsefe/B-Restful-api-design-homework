package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Student {
    @NotNull
    @Min(1)
    private final Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    private String note;

}
