package com.droppages.Skepter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class JoinListener implements Listener {

	NecessaryExtrasCore plugin;
	public JoinListener(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
//		Player player = event.getPlayer();
//		if (plugin.check(player) == false) {
//			plugin.Add(player);
//		}
//		return;
	}
}
