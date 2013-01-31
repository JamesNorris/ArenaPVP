package com.github.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.DataHolder;
import com.github.Game;
import com.github.Team;
import com.github.threading.RespawnThread;

public class PlayerDeath extends DataHolder implements Listener {
    
    @EventHandler
    public void PDE(PlayerDeathEvent event) {
        Player player = event.getEntity();
        for (Game game : games) {
            Team team = game.getPlayerTeam(player);
            if (team != null) {
                new RespawnThread(game, team, player, game.type.getRespawnTimer(), true);
            }
        }
    }
}
