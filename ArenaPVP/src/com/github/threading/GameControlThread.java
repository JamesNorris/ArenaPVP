package com.github.threading;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.DataHolder;
import com.github.Game;
import com.github.Team;

public class GameControlThread extends DataHolder implements GameThread {
    public boolean runThrough = false;
    public int interval, count = 0, startCount = 0;
    public Game game;
    public HashMap<String, Location> startLocations = new HashMap<String, Location>();

    public GameControlThread(Game game, boolean autorun) {
        this.interval = 20;
        this.game = game;
        threads.add(this);
        if (autorun)
            setRunThrough(true);
    }

    @Override public boolean runThrough() {
        return runThrough;
    }

    @Override public void setRunThrough(boolean tf) {
        runThrough = tf;
    }

    @Override public void run() {
        if (game.isReady()) {
            if (++startCount >= 60) {
                game.start();
                startCount = 0;
            }
        } else {
            startCount = 0;
        }
        ArrayList<Location> objectives = game.arena.objectives;
        // keeps players in the same location while the game is beginning
        for (Team team : game.matchup) {
            ++game.time;
            for (String name : team.teamMates) {
                Player player = Bukkit.getPlayer(name);
                if (!startLocations.containsKey(name)) {
                    startLocations.put(name, player.getLocation());
                } else {
                    player.teleport(startLocations.get(name));
                }
                for (Location objective : objectives) {
                    if (player.getLocation().distance(objective) <= game.type.getLocationObjectiveCompleteDistance()) {
                        game.type.onLocationObjectiveComplete(game, player, team, game.spawns);
                    }
                }
            }
            // team leveling for fairness
            if (!game.hasClan()) {
                for (Team team2 : game.matchup) {
                    if (team2.teamMates.length + 2 < team.teamMates.length) {
                        String trade = team.teamMates[0];
                        team.removeTeammate(trade);
                        team2.addTeammate(trade);
                    }
                }
            }
            // checking for game-ending scenarios
            if (team.points >= game.type.getScoreToWin())
                game.win(team);
            if (game.time * 60 >= game.type.getMaxGameTimeInMinutes())
                game.end();
        }
    }

    @Override public void remove() {
        threads.remove(this);
    }

    @Override public int getCount() {
        return count;
    }

    @Override public int getInterval() {
        return interval;
    }

    @Override public void setCount(int i) {
        count = i;
    }

    @Override public void setInterval(int i) {
        interval = i;
    }
}
