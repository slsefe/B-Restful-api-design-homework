package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.InvalidTeamException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.thoughtworks.capability.gtb.restfulapidesign.common.Constants.COUNT_OF_TEAM;

@Service
public class TeamService {
    private final Map<Integer, Team> teamMap = new HashMap<>();

    public TeamService() {
        initTeams();
    }

    private void initTeams() {
        for (int i = 1; i <= COUNT_OF_TEAM; i++) {
            teamMap.put(i, new Team(i, "team" + i, "team" + i));
        }
    }

    public List<Team> getAll() {
        return new ArrayList<>(teamMap.values());
    }

    public Team getById(Integer id) {
        Team team = teamMap.get(id);
        if (team == null) {
            throw new InvalidTeamException("team not found");
        }
        return team;
    }

    public Team updateName(Integer id, String name) {
        Team team = getById(id);
        team.setName(name);
        teamMap.put(id, team);
        return team;
    }
}
