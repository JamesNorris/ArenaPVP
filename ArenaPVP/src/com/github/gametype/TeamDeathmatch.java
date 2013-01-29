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

    @Override public HashMap<Team, Location> getLocationObjectives() {
        return null;
    }

    @Override public boolean usesLocationObjectives() {
        return false;
    }

    @Override public void onLocationObjectiveComplete(Game game, Player player, Team[] teams, Location[] teamspawns) {
        //there is none...
    }

    @Override public int getMaxGameTimeInMinutes() {
        return 15;
    }
}
