package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Team;

import java.util.Collection;

public interface TeamDAO {
    int createTeam(Team team);
    boolean updateTeam(int teamId);
    boolean deleteTeam(int teamId);
    Team findTeam(int teamId);
    Collection<Team> findAll();
}
