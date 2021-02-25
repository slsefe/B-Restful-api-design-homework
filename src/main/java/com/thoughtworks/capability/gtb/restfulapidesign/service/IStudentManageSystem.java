package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;

import java.util.List;

public interface IStudentManageSystem {
    Student addStudent(String name, String gender, String note);

    void deleteStudent(Integer id);

    List<Student> getAllStudents(String name, String gender);

    Student getStudentById(Integer id);

    List<Team> getAllTeams();

    Student updateStudentInfo(Integer id, String name, String gender, String note);

    Team updateTeamName(Integer id, String name);

    void grouping();
}
