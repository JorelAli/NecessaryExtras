package com.droppages.Skepter.api;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SignEditEvent extends Event implements Cancellable{
	
    private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Sign sign;
	private String text;
	private int line;
    private boolean cancelled;
	 
    public SignEditEvent(Player player, String text, int line, Sign sign) {
        this.player = player;
        this.sign = sign;
        this.text = text;
        this.line = line;
    }
 
    public String getText() {
        return text;
    }
    
    public Sign getSign() {
    	return sign;
    }
    
    public int getLine() {
    	return line;
    }
    
    public Player getPlayer() {
    	return player;
    }
 
    public void setLine(int line) {
    	this.line = line;
    }
    
    public void setText(String text) {
    	this.text = text;
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
