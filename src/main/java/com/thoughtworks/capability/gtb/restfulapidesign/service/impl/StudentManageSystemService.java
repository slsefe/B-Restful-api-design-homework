package com.thoughtworks.capability.gtb.restfulapidesign.service.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.IStudentManageSystem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentManageSystemService implements IStudentManageSystem {
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final Map<Integer, Team> teamMap = new HashMap<>();
    private final static int COUNT_OF_TEAM = 6;
    private int nextStudentID = 1;

    public StudentManageSystemService() {
        for (int i = 1; i <= COUNT_OF_TEAM; i++) {
            this.teamMap.put(i, new Team(i, String.format("team%d", i), null));
        }
    }

    @Override
    public Student addStudent(String name, String gender, String note) {
        Student student = new Student(nextStudentID, name, gender, note);
        studentMap.put(student.getId(), student);
        nextStudentID += 1;
        return student;
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMap.remove(id);
    }

    @Override
    public List<Student> getAllStudents(String name, String gender) {
        return studentMap.values().stream()
                .filter(student -> name == null || student.getName().equals(name))
                .filter(student -> gender == null || student.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @Override
    public Student getStudentById(Integer id) {
        Student student = studentMap.get(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student not found");
        }
        return student;
    }

    @Override
    public Student updateStudentInfo(Integer id, String name, String gender, String note) {
        Student student = getStudentById(id);
        student.setName(name);
        student.setGender(gender);
        student.setNote(note);
        return student;
    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teamMap.values());
    }

    @Override
    public Team updateTeamName(Integer id, String name) {
        Team team = getTeamById(id);
        team.setName(name);
        return team;
    }

    private Team getTeamById(Integer id) {
        Team team = teamMap.get(id);
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "team not found");
        }
        return team;
    }

    @Override
    public void grouping() {
        for (Team team : teamMap.values()) {
            team.clearStudent();
        }

        List<Integer> studentIds = new ArrayList<>(studentMap.keySet());
        Random random = new Random();
        int teamIndex = 1;

        while (!studentIds.isEmpty()) {
            Integer randomStudentID = studentIds.get(random.nextInt(studentIds.size()));
            studentIds.remove(randomStudentID);
            teamMap.get(teamIndex).addStudent(randomStudentID);
            teamIndex = (teamIndex + 1) % COUNT_OF_TEAM;
        }
    }
}
