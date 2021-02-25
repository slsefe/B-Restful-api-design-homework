package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();

    Team getById(Integer id);

    Team updateName(Integer id, String name);
}
