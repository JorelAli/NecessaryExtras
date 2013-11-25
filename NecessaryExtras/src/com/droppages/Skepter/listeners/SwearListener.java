package com.droppages.Skepter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class SwearListener implements Listener {

	NecessaryExtrasCore plugin;
	public SwearListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		for(String s : plugin.getConfig().getStringList("SwearWords")) {
			if(event.getMessage().toLowerCase().contains(s.toLowerCase())) {
				if(!(event.getPlayer().hasPermission("NE.bypassswear")) || !event.getPlayer().isOp()) {
					event.getPlayer().sendMessage(plugin.prefix + plugin.getConfig().getString("SwearMessage"));
					event.setCancelled(true);
					return;
				} else {
					return;
				}
			}
		}
	}
}
