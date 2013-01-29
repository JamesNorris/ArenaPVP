package com.github.threading;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.Arena;
import com.github.DataHolder;
import com.github.Team;

public class ArenaGuardThread extends DataHolder implements GameThread {
    public Arena arena;
    public boolean runThrough = false;
    public int interval, count = 0;
    public HashMap<String, Integer> warnings = new HashMap<String, Integer>();

    public ArenaGuardThread(Arena arena, boolean autorun) {
        this.arena = arena;
        this.interval = 20;// default
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
        if (!arena.game.underway)
            setRunThrough(false);
        for (Team team : arena.game.matchup) {
            for (String name : team.teamMates) {
                Player player = Bukkit.getPlayer(name);
                if (!arenaContainsXandZ(player.getLocation())) {
                    if (!warnings.containsKey(name)) {
                        warnings.put(name, 5);
                    } else {
                        int currentWarn = warnings.get(name);
                        warnings.remove(name);
                        warnings.put(name, --currentWarn);
                    }
                    int current = warnings.get(name);
                    if (current <= 5) {
                        player.sendMessage(ChatColor.RED + "You are leaving the combat zone! <" + current + ">");
                        if (current == 0) {
                            warnings.remove(name);
                            player.teleport(arena.game.spawns.get(team));
                        }
                    }
                }
            }
        }
    }

    private boolean arenaContainsXandZ(Location loc) {
        int lowX = arena.region.lowX;
        int lowZ = arena.region.lowZ;
        int highX = arena.region.highX;
        int highZ = arena.region.highZ;
        boolean Xs = overlap_1D(lowX, highX, loc.getBlockX(), loc.getBlockX());
        boolean Zs = overlap_1D(lowZ, highZ, loc.getBlockZ(), loc.getBlockZ());
        return (Xs && Zs);
    }

    private boolean overlap_1D(double low1, double high1, double low2, double high2) {
        if (low1 <= low2)
            return (low2 <= high1);
        return (low1 <= high2);
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
