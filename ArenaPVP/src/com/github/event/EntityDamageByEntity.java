package com.github.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.DataHolder;
import com.github.Game;

public class EntityDamageByEntity extends DataHolder implements Listener {
    
    @EventHandler
    public void EDBEE(EntityDamageByEntityEvent event) {
        Entity evictim = event.getEntity();
        Entity edamager = event.getDamager();
        if (evictim instanceof Player) {
            Player victim = (Player) evictim;
            if (isInGame(victim)) {
                if (edamager instanceof Player) {
                    Player damager = (Player) edamager;
                    if (!isInGame(damager)) {
                        event.setCancelled(true);
                        damager.sendMessage(ChatColor.RED + "This player is in a game and cannot currently be damaged!");
                    }
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    public boolean isInGame(Player player) {
        for (Game game : games) {
            if (game.getPlayerTeam(player) != null)
                return true;
        }
        return false;
    }
}
