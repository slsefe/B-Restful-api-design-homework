package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@Validated
@Slf4j
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAll(@RequestParam(required = false) String gender) {
        return this.studentService.getAll(null, gender);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable @Min(1) Integer id) {
        return studentService.getById(id);
    }

    @PostMapping
    public Student create(@RequestParam String name,
                       @RequestParam String gender,
                       @RequestParam(name = "note", required = false) String note) {
        return studentService.create(name, gender, note);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable @Min(1) Integer id,
                          @RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "gender", required = false) String gender,
                          @RequestParam(name = "note", required = false) String note) {
        return studentService.updateInfo(id, name, gender, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) Integer id) {
        studentService.deleteById(id);
    }

    @GetMapping("/groups")
    public Map<Integer, Set<Integer>> group() {
        return studentService.grouping();
    }
}
