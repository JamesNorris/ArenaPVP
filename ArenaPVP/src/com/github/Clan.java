package com.github;

import java.util.ArrayList;

import org.bukkit.Bukkit;

public class Clan {
    public String owner, clanName;
    public ArrayList<String> members = new ArrayList<String>(); // contains owner too

    public Clan(String owner) {
        this.owner = owner;
        if (!members.contains(owner))
            members.add(owner);
    }

    public void addMember(String name) {
        if (!members.contains(name)) {
            members.add(name);
            Bukkit.getPlayer(name).sendMessage("You have joined the clan " + clanName + ".");
        }
    }

    public void removeMember(String name) {
        if (members.contains(name)) {
            members.remove(name);
            Bukkit.getPlayer(name).sendMessage("You have been removed from the clan " + clanName + ".");
        }
    }
}
