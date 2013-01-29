package com.github.threading;

public interface GameThread {
    public boolean runThrough();
    
    public void setRunThrough(boolean tf);
    
    public void run();
    
    public void remove();
    
    public int getCount();
    
    public int getInterval();
    
    public void setCount(int i);
    
    public void setInterval(int i);
}
