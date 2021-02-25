package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentService {
    Student create(String name, String gender, String note);

    void deleteById(Integer id);

    List<Student> getAll(String name, String gender);

    Student getById(Integer id);

    Student updateInfo(Integer id, String name, String gender, String note);

    Map<Integer, Set<Integer>> grouping();
}
