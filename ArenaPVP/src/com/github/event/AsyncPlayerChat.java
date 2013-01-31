package com.github.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.DataHolder;
import com.github.Game;
import com.github.Team;

public class AsyncPlayerChat extends DataHolder implements Listener {
    private static String teamTag = ChatColor.BLUE + "[TEAM]" + ChatColor.RESET;
    
    @EventHandler
    public void PCE(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String message = event.getMessage();
        for (Game game : games) {
            for (Team team : game.matchup) {
                if (team.getNameInTeammates(name) != -1) {
                    if (!event.isCancelled())
                        event.setCancelled(true);
                    for (String mate : team.teamMates) {
                        if (!name.equalsIgnoreCase(mate)) {
                            Bukkit.getPlayer(mate).sendMessage(teamTag + message);
                        }
                    }
                }
            }
        }
    }
}
