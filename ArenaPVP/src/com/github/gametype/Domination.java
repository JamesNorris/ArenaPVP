package com.github.gametype;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.Game;
import com.github.GameType;
import com.github.Team;

public class Domination implements GameType {

    @Override public int getMaxTeamSize() {
        return 10;
    }

    @Override public int getNumberOfLocationObjectives() {
        return 3;
    }

    @Override public int getMaxNumberOfTeams() {
        return 2;
    }

    @Override public int getMinTeamSize() {
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
        return "domination";
    }

    @Override public int getScoreToWin() {
        return 200;
    }

    @Override public boolean usesLocationObjectives() {
        return true;
    }

    @Override public int getMaxGameTimeInMinutes() {
        return 20;
    }

    @Override public int getNumberOfRounds() {
        return 1;
    }

    @Override public int getLocationObjectiveCompleteDistance() {
        return 3;
    }

    @Override public void onLocationObjectiveComplete(Game game, Player player, Team team, HashMap<Team, Location> teamspawns) {
        // TODO Auto-generated method stub
        
    }    
}
