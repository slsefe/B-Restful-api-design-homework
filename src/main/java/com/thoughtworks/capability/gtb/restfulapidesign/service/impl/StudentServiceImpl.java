package com.thoughtworks.capability.gtb.restfulapidesign.service.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.InvalidStudentException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final static int COUNT_OF_TEAM = 6;
    private int nextStudentID = 1;

    @Override
    public Student create(String name, String gender, String note) {
        Student student = new Student(nextStudentID, name, gender, note);
        studentMap.put(student.getId(), student);
        nextStudentID += 1;
        return student;
    }

    @Override
    public void deleteById(Integer id) {
        Student student = getById(id);
        studentMap.remove(id, student);
    }

    @Override
    public List<Student> getAll(String name, String gender) {
        return studentMap.values().stream()
                .filter(student -> name == null || student.getName().equals(name))
                .filter(student -> gender == null || student.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @Override
    public Student getById(Integer id) {
        Student student = studentMap.get(id);
        if (student == null) {
            throw new InvalidStudentException();
        }
        return student;
    }

    @Override
    public Student updateInfo(Integer id, String name, String gender, String note) {
        Student student = getById(id);
        if (name != null) {
            student.setName(name);
        }
        if (gender != null) {
            student.setGender(gender);
        }
        if (note != null) {
            student.setNote(note);
        }
        return student;
    }

    @Override
    public Map<Integer, Set<Integer>> grouping() {
        List<Integer> studentIds = new ArrayList<>(studentMap.keySet());
        Map<Integer, Set<Integer>> groupStudent = new HashMap<>();
        Random random = new Random();
        int teamIndex = 1;

        while (!studentIds.isEmpty()) {
            Integer randomStudentID = studentIds.get(random.nextInt(studentIds.size()));
            studentIds.remove(randomStudentID);
            groupStudent.get(teamIndex).add(randomStudentID);
            teamIndex = (teamIndex + 1) % COUNT_OF_TEAM;
        }
        return groupStudent;
    }
}
