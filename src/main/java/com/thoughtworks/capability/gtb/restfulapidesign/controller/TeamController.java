package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/teams")
@Slf4j
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public List<Team> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable @Min(1) Integer id) {
        return teamService.getById(id);
    }

    @PutMapping("/{id}")
    public Team update(@PathVariable @Min(1) Integer id,
                       @NotBlank String name) {
        return teamService.updateName(id, name);
    }
}
