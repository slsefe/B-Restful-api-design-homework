package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.InvalidStudentException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.thoughtworks.capability.gtb.restfulapidesign.common.Constants.COUNT_OF_TEAM;


@Service
public class StudentService  {
    private final Map<Integer, Student> studentMap = new ConcurrentHashMap<>();

    private final Map<Integer, Set<Integer>> groupingMap = new ConcurrentHashMap<>();

    private final AtomicInteger nextStudentID = new AtomicInteger(0);

    public StudentService() {
        initGroupMap();
    }

    public Student create(String name, String gender, String note) {
        Student student = new Student(nextStudentID.incrementAndGet(), name, gender, note);
        studentMap.put(student.getId(), student);
        return student;
    }

    public void deleteById(Integer id) {
        studentMap.remove(id);
    }

    public List<Student> getAll(String gender) {
        if (StringUtils.isEmpty(gender)) {
            return new ArrayList<>(studentMap.values());
        }
        return studentMap.values().stream()
                .filter(student -> student.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Student getById(Integer id) {
        Student student = studentMap.get(id);
        if (student == null) {
            throw new InvalidStudentException();
        }
        return student;
    }

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
        studentMap.put(id, student);
        return student;
    }

    public Map<Integer, Set<Integer>> grouping() {
        List<Integer> studentIds = new ArrayList<>(studentMap.keySet());
        initGroupMap();
        int teamId = 1;
        for (int id : studentIds) {
            groupingMap.get(teamId).add(id);
            teamId = teamId % COUNT_OF_TEAM + 1;
        }
        return groupingMap;
    }

    private void initGroupMap() {
        groupingMap.clear();
        for (int i = 1; i <= COUNT_OF_TEAM; i++) {
            groupingMap.put(i, new HashSet<>());
        }
    }
}
