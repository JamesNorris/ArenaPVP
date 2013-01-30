package com.github;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface GameType {
    public int getScoreToWin();

    public int getMaxTeamSize();

    public int getMinTeamSize();

    public int getMaxNumberOfTeams();

    public int getMinNumberOfTeams();

    public boolean hasRespawnTimer();

    public int getRespawnTimer();

    public String getName();

    public int getNumberOfRounds();

    public int getMaxGameTimeInMinutes();

    public boolean usesLocationObjectives();

    public int getLocationObjectiveCompleteDistance();

    public int getNumberOfLocationObjectives();

    public void onLocationObjectiveComplete(Game game, Player player, Team team, HashMap<Team, Location> teamspawns);
}
