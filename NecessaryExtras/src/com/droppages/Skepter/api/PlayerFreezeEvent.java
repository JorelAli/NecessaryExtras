package com.droppages.Skepter.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerFreezeEvent extends Event implements Cancellable{
	
    private static final HandlerList handlers = new HandlerList();
	private Player player;
    private boolean cancelled;
	 
    public PlayerFreezeEvent(Player player) {
        this.player = player;

    }

    public Player getPlayer() {
    	return player;
    }

    public boolean isCancelled() {
        return cancelled;
    }
 
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
