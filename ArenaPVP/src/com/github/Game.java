package com.github;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.threading.GameControlThread;

public class Game {
    public GameType type;
    public Team[] matchup;
    public int teamCount = 0;
    public boolean underway = false;
    public Arena arena;
    public GameControlThread gameStartThread;
    public HashMap<Team, Location> spawns = new HashMap<Team, Location>();

    public Game(GameType type) {
        this.type = type;
        matchup = new Team[type.getMaxNumberOfTeams()];
        gameStartThread = new GameControlThread(this, true);
    }

    public void loadInTeam(Team team) {
        matchup[teamCount] = team;
        ++teamCount;
    }

    public void setArena(Region region) {
        arena = new Arena(region, this);
    }

    public void start() {
        if (spawns.isEmpty())
            spawns = arena.createTeamSpawns();
        for (Team team : spawns.keySet()) {
            Location spawn = spawns.get(team);
            for (String name : team.teamMates) {
                Bukkit.getPlayer(name).teleport(spawn);
            }
        }
        arena.guardThread.setRunThrough(true);
        gameStartThread.setRunThrough(true);
        underway = true;
    }

    public void end() {
        arena.guardThread.setRunThrough(false);
        gameStartThread.setRunThrough(false);
        underway = false;
    }

    public boolean isReady() {
        for (Team team : matchup)
            if (team.teamMates.length < type.getMinTeamSize())
                return false;
        return true;
    }

    public boolean isUnderway() {
        return underway;
    }
    
    public Team getPlayerTeam(Player player) {
        for (Team team : matchup)
            if (team.getNameInTeammates(player.getName()) != -1)
                return team;
        return null;
    }
}
