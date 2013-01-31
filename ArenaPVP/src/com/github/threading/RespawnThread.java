package com.github.threading;

import org.bukkit.entity.Player;

import com.github.DataHolder;
import com.github.Game;
import com.github.Team;

public class RespawnThread extends DataHolder implements GameThread {
    public boolean runThrough = false;
    public int interval, count = 0, countdown;
    public Player player;
    public Team team;
    public Game game;
    
    public RespawnThread(Game game, Team team, Player player, int countdown, boolean autorun) {
        this.game = game;
        this.team = team;
        this.player = player;
        this.interval = 20;
        this.countdown = countdown;
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
        if (--countdown <= 0) {
            player.teleport(game.spawns.get(team));
            remove();
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
