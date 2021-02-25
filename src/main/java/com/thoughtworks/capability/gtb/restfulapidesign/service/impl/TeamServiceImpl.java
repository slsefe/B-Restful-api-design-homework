package com.thoughtworks.capability.gtb.restfulapidesign.service.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {
    private final Map<Integer, Team> teamMap;

    public TeamServiceImpl() {
        teamMap = new HashMap<>();
    }

    @Override
    public List<Team> getAll() {
        return new ArrayList<>(teamMap.values());
    }

    public Team getById(Integer id) {
        Team team = teamMap.get(id);
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "team not found");
        }
        return team;
    }

    @Override
    public Team updateName(Integer id, String name) {
        Team team = getById(id);
        team.setName(name);
        return team;
    }
}
