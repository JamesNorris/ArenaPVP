package com.github;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.threading.ArenaGuardThread;

public class Arena {
    public Region region;
    public Game game = null;
    public ArenaGuardThread guardThread;
    public Random rand = new Random();
    
    public Arena(Region region, Game game) {
        this.region = region;
        this.game = game;
        guardThread = new ArenaGuardThread(this, false);
    }
    
    public HashMap<Team, Location> createTeamSpawns() {
        HashMap<Team, Location> spawns = new HashMap<Team, Location>();
        int amount = game.teamCount;
        int width = region.highX - region.lowX;
        int length = region.highZ - region.lowZ;
        int availableXs = rand.nextInt(width / amount);
        int availableZs = rand.nextInt(length / amount);
        int modifier = -3;
        for (int i = 1; i <= amount; i++) {
            if (i == 1)
                modifier = -3;
            else if (i == amount)
                modifier = 3;
            else
                modifier = 0;
            int X = (availableXs * i) + modifier;
            int Z = (availableZs * i) + modifier;
            int Y = getNearestOpenY(region.world, X, (region.highY - region.lowY), Z);
            spawns.put(game.matchup[i], region.world.getBlockAt(X, Y, Z).getLocation());
        }
        return spawns;
    }
    
    public int getNearestOpenY(World world, int X, int Y, int Z) {
        for (int i = 0; i <= 30; i++) {
            int highY = Y + i;
            int lowY = Y - i;
            boolean highYopen = world.getBlockAt(X, highY, Z).isEmpty();
            boolean highYclosed = world.getBlockAt(X, highY + 1, Z).isEmpty();
            boolean lowYopen = world.getBlockAt(X, lowY, Z).isEmpty();
            boolean lowYclosed = world.getBlockAt(X, lowY - 1, Z).isEmpty();
            if (lowYopen && !lowYclosed)
                return lowY;
            if (highYopen && !highYclosed)
                return highY;
        }
        return Y;
    }
}
