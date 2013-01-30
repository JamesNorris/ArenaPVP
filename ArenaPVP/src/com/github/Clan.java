package com.github;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Clan {
    public String owner, clanName;
    public ArrayList<String> members = new ArrayList<String>(); // contains owner too

    protected Clan(String owner, String clanName) {
        this.owner = owner;
        this.clanName = clanName;
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

    public void disband() {
        for (String name : members)
            Bukkit.getPlayer(name).sendMessage("Your clan " + clanName + " has been disbanded!");
        members.clear();
    }

    public static Clan create(String owner, String clanName) {
        Player player = Bukkit.getPlayer(owner);
        if ((Boolean) Setting.SPECIFIC_CLAN_CREATION_PERMS.object || player.hasPermission("apvp.clancreate"))
            return new Clan(owner, clanName);
        return null;
    }
}
