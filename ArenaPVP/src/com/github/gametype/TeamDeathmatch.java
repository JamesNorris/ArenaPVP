package com.github.gametype;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.Game;
import com.github.GameType;
import com.github.Team;

public class TeamDeathmatch implements GameType {
    
    @Override public int getScoreToWin() {
        return 30;
    }

    @Override public int getMaxTeamSize() {
        return 6;
    }

    @Override public int getMinTeamSize() {
        return 2;
    }

    @Override public int getMaxNumberOfTeams() {
        return 2;
    }

    @Override public int getMinNumberOfTeams() {
        return 2;
    }

    @Override public boolean hasRespawnTimer() {
        return true;
    }

    @Override public int getRespawnTimer() {
        return 5;
    }

    @Override public String getName() {
        return "team deathmatch";
    }

    @Override public int getNumberOfLocationObjectives() {
        return 0;
    }

    @Override public boolean usesLocationObjectives() {
        return false;
    }

    @Override public int getMaxGameTimeInMinutes() {
        return 15;
    }

    @Override public int getNumberOfRounds() {
        return 1;
    }

    @Override public int getLocationObjectiveCompleteDistance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override public void onLocationObjectiveComplete(Game game, Player player, Team team, HashMap<Team, Location> teamspawns) {
        //none
    }
}
