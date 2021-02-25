package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private final Integer id;
    private String name;
    private String note;
}
