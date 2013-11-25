package com.droppages.Skepter.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class CapsListener implements Listener {

	NecessaryExtrasCore plugin;
	public CapsListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = (Player) event.getPlayer();
			
			double uppercharacters = 0.0D;
			double totalcharacters = 0.0D;
			double percentage = 0.0D;
			String msg = event.getMessage().replaceAll(" ", "");
			for(int i = 0; i < msg.length(); i++) {
				if(Character.isUpperCase(msg.charAt(i))) {
					uppercharacters++;
					totalcharacters++;
				}
				if(Character.isLowerCase(msg.charAt(i))) {
					totalcharacters++;
				}
			}
			
			percentage = uppercharacters / totalcharacters;
			percentage *= 100D;
			if(CapsPercent() == 0) {
				return;
			}
			if(player.hasPermission("NE.bypasscaps") || player.isOp() || percentage <= CapsPercent()) {
				return;
			} else {
				event.setMessage(event.getMessage().toLowerCase());
			}
	}
	
	public double CapsPercent() {
		double percentage = 0;
		try {
			percentage = plugin.getConfig().getDouble("CapsPercentage");
		} catch (Exception e) {
			percentage = 60;
		}
		return percentage;
	}
	
}
