package com.github.gametype;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.Game;
import com.github.GameType;
import com.github.Team;

public class FreeForAll implements GameType {

    @Override public int getScoreToWin() {
        return 15;
    }

    @Override public int getMaxTeamSize() {
        return 1;
    }

    @Override public int getMinTeamSize() {
        return 1;
    }

    @Override public int getMaxNumberOfTeams() {
        return 10;
    }

    @Override public int getMinNumberOfTeams() {
        return 7;
    }

    @Override public boolean hasRespawnTimer() {
        return false;
    }

    @Override public int getRespawnTimer() {
        return 0;
    }

    @Override public String getName() {
        return "free for all";
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
        return 10;
    }
}
