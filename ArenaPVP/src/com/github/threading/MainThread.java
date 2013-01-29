package com.github.threading;

import org.bukkit.Bukkit;

import com.github.DataHolder;
import com.github.Main;

public class MainThread extends DataHolder {
    private int id = -1, interval = 1;

    public MainThread(boolean autorun) {
        if (autorun)
            run();
    }

    public void run() {
        if (id == -1) {
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
                @Override public void run() {
                    tick();
                }
            }, interval, interval);
        }
    }

    public synchronized void tick() {
        for (GameThread thread : threads) {
            thread.setCount(thread.getCount() + 1);
            if (thread.runThrough() && (thread.getCount() >= thread.getInterval())) {
                thread.run();
                thread.setCount(0);
            }
        }
    }

    public synchronized void cancel() {
        Bukkit.getScheduler().cancelTask(id);
        id = -1;
    }
}
