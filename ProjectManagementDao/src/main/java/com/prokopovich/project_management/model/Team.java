package com.prokopovich.project_management.model;

public class Team {
    int teamId;
    String teamName;
    int participantsNumber;

    public Team() {}

    public Team(int teamId, String teamName, int participantsNumber) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.participantsNumber = participantsNumber;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(int participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    @Override
    public String toString() {
        return "Team: " +
                "id = " + teamId +
                ", teamName = " + teamName +
                ", participantsNumber = " + participantsNumber + ';';
    }
}
