package com.github;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.event.AsyncPlayerChat;
import com.github.event.EntityDamageByEntity;
import com.github.threading.MainThread;

public class Main extends JavaPlugin {
    public static Main instance;
    public static DataHolder data;
    public static MainThread main;
    
    @Override public void onEnable() {
        Main.instance = this;
        data = new DataHolder();
        main = new MainThread(true);
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            saveDefaultConfig();
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new AsyncPlayerChat(), this);
        pm.registerEvents(new EntityDamageByEntity(), this);
    }
}
