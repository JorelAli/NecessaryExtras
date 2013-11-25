package com.droppages.Skepter.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class VoidListener implements Listener {

	NecessaryExtrasCore plugin;
	public VoidListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if(plugin.getConfig().getBoolean("VoidToSpawn")) {
			if(player.getLocation().getY() <= 0) {
				player.setFallDistance(0);
				player.teleport(player.getWorld().getSpawnLocation());
			}
		}
	}
}
