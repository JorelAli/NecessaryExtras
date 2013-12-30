package com.droppages.Skepter.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class CodeChatListener implements Listener {

	NecessaryExtrasCore plugin;
	public CodeChatListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(event.getMessage().startsWith("[Code]") || event.getMessage().startsWith("[code]")) {
			event.setCancelled(true);
			String subString = event.getMessage().substring(6, event.getMessage().length());
			event.getPlayer().chat(ChatColor.RESET + subString);
		}
	}
}
