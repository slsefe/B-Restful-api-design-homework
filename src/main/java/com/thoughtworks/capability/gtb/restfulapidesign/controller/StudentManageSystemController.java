package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.IStudentManageSystem;
import com.thoughtworks.capability.gtb.restfulapidesign.service.impl.StudentManageSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class StudentManageSystemController {
    IStudentManageSystem sms = new StudentManageSystemService();

    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam(name = "gender", required = false) String gender) {
        return sms.getAllStudents(null, gender);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable @Min(1) Integer id) {
        return sms.getStudentById(id);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestParam(name = "name") @NotBlank String name,
                              @RequestParam(name = "gender") @NotBlank String gender,
                              @RequestParam(name = "note", required = false) String note) {
        return sms.addStudent(name, gender, note);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable @Min(1) Integer id,
                                 String name, String gender, String note) {
        return sms.updateStudentInfo(id, name, gender, note);
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable @Min(1) Integer id) {
        sms.deleteStudent(id);
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return sms.getAllTeams();
    }

    @PutMapping("/teams/{id}")
    public Team updateTeamName(@PathVariable @Min(1) Integer id,
                               @NotNull String name) {
        return sms.updateTeamName(id, name);
    }

    @GetMapping("/teams/grouping")
    public List<Team> grouping() {
        sms.grouping();
        return sms.getAllTeams();
    }
}
