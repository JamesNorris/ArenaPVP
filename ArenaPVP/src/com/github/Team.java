package com.github;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Team {
    private static int TEAM_ID = 0;
    public String[] teamMates;
    public int currentSize = 0, teamId, points;
    public GameType gameType;
    public Game currentGame = null;
    public Clan clan;

    public Team(GameType gameType) {
        this.gameType = gameType;
        teamMates = new String[gameType.getMaxTeamSize()];
        teamId = ++Team.TEAM_ID;
    }
    
    public Team(GameType gameType, Clan clan) {
        this.gameType = gameType;
        teamMates = new String[gameType.getMaxTeamSize()];
        teamId = ++Team.TEAM_ID;
    }

    public void addTeammate(String name) {
        if (teamMates.length < gameType.getMaxTeamSize()) {
            ++currentSize;
            teamMates[currentSize] = name;
        }
    }

    public void removeTeammate(String name) {
        int playerNumber = getNameInTeammates(name);
        if (playerNumber != -1) {
            --currentSize;
            for (int i = playerNumber; i <= teamMates.length; i++) {
                if (teamMates[playerNumber + 1] != null) {
                    teamMates[i] = teamMates[playerNumber + 1];
                } else {
                    teamMates[i] = "NO_PLAYER_ASSIGNED";
                }
            }
        }
    }

    protected int getNameInTeammates(String name) {
        for (int i = 0; i <= teamMates.length; i++) {
            if (teamMates[i] == name) {
                return i;
            }
        }
        return -1;
    }

    public void remove() {
        for (int i = 0; i <= teamMates.length; i++) {
            String name = teamMates[i];
            if (name != null && name != "NO_PLAYER_ASSIGNED") {
                Player player = Bukkit.getPlayer(name);
                if (player != null) {
                    player.sendMessage("Your team for " + gameType.getName() + " has been disbanded.");
                    teamMates[i] = "NO_PLAYER_ASSIGNED";
                }
            }
        }
    }
}
