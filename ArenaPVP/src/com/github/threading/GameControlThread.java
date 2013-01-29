package com.github.threading;

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
        // keeps players in the same location while the game is beginning
        for (Team team : game.matchup) {
            for (String name : team.teamMates) {
                Player player = Bukkit.getPlayer(name);
                if (!startLocations.containsKey(name)) {
                    startLocations.put(name, player.getLocation());
                } else {
                    player.teleport(startLocations.get(name));
                }
            }
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
