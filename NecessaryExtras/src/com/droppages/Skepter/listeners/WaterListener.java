package com.droppages.Skepter.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class WaterListener implements Listener {

	NecessaryExtrasCore plugin;

	public WaterListener(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlace(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& event.getClickedBlock().getWorld().getEnvironment() == World.Environment.NETHER
				&& event.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET
				&& plugin.getConfig().getBoolean("NetherWater") == true) {
			event.getClickedBlock().getRelative(event.getBlockFace())
					.setType(Material.WATER);
			;
		} else {
			return;
		}
	}
}
