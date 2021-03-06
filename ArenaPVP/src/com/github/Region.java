package com.github;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Region {
	public Location loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8;
	public World world;
	public int highX, highZ, lowX, lowZ, lowY, highY;
	
	/**
	 * Creates a new 3D region from 2 corner points.
	 * @param start The first corner selected
	 * @param end The second corner selected
	 */
	public Region(Location start, Location end) {
		world = start.getWorld();
		
		int startX = start.getBlockX();
		int startY = start.getBlockY();
		int startZ = start.getBlockZ();
		int endX = end.getBlockX();
		int endY = end.getBlockY();
		int endZ = end.getBlockZ();
		
		highX = (startX > endX) ? startX : endX;
		lowX = (startX <= endX) ? startX : endX;
		highY = (startY > endY) ? startY : endY;
		lowY = (startY <= endY) ? startY : endY;
		highZ = (startZ > endZ) ? startZ : endZ;
		lowZ = (startZ <= endZ) ? startZ : endZ;
		
		loc1 = world.getBlockAt(lowX, highY, highZ).getLocation();
		loc2 = world.getBlockAt(highX, highY, highZ).getLocation();
		loc3 = world.getBlockAt(highX, highY, lowZ).getLocation();
		loc4 = world.getBlockAt(lowX, highY, lowZ).getLocation();
		loc5 = world.getBlockAt(lowX, lowY, highZ).getLocation();
		loc6 = world.getBlockAt(highX, lowY, highZ).getLocation();
		loc7 = world.getBlockAt(highX, lowY, lowZ).getLocation();
		loc8 = world.getBlockAt(lowX, lowY, lowZ).getLocation();
	}
	
	/**
	 * Gets the corner that matches the given number.
	 * @param corner The corner number from 1-8
	 * @return The corner corresponding to the number
	 */
	public Location getCorner(int corner) {
		Location[] locs = new Location[] { loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8 };
		return locs[corner];
	}
	
	/*
	 * Checks a 1D rectangle for overlap.
	 */
	private boolean overlap_1D(double low1, double high1, double low2, double high2) {
	    if (low1 <= low2) 
	    	return (low2 <= high1);
	    return (low1 <= high2);
	}
	
	/**
	 * Checks if the given region touches or overlaps this region.
	 * @param other The region to check for
	 * @return Whether or not they touch or overlap
	 */
	public boolean overlaps(Region other) {
	    boolean Xs = overlap_1D(lowX, highX, other.lowX, other.highX);
	    boolean Ys = overlap_1D(lowY, highY, other.lowY, other.highY);
	    boolean Zs = overlap_1D(lowZ, highZ, other.lowZ, other.highZ);
	    return (Xs && Ys && Zs);  	
	}
	
	/**
	 * Checks if the location is contained inside the region.
	 * @param loc The location to check for
	 * @return Whether or not the location is contained in the region
	 */
	public boolean contains(Location loc) {
	    boolean Xs = overlap_1D(lowX, highX, loc.getBlockX(), loc.getBlockX());
	    boolean Ys = overlap_1D(lowY, highY, loc.getBlockY(), loc.getBlockY());
	    boolean Zs = overlap_1D(lowZ, highZ, loc.getBlockZ(), loc.getBlockZ());
		return (Xs && Ys && Zs);	
	}
	
	/**
	 * Checks if the player is contained inside the region.
	 * @param p The player to check for
	 * @return Whether or not the player is contained in the region
	 */
	public boolean contains(Player p) {
		return contains(p.getLocation());
	}
}
