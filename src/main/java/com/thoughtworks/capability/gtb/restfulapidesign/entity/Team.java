package com.thoughtworks.capability.gtb.restfulapidesign;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Team {
    private final Integer id;
    private String name;
    private String note;
    private final List<Integer> studentIds = new ArrayList<>();

    public void addStudent(Integer id) {
        studentIds.add(id);
    }

    public void clearStudent() {
        this.studentIds.clear();
    }
}
