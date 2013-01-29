package com.github;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.threading.MainThread;

public class Main extends JavaPlugin {
    public static Main instance;
    public static DataHolder data;
    public static MainThread main;
    
    @Override public void onEnable() {
        Main.instance = this;
        data = new DataHolder();
        main = new MainThread(true);
    }
}
