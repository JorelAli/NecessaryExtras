package com.droppages.Skepter.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class DoubleJumpListener implements Listener {

	NecessaryExtrasCore plugin;
	public DoubleJumpListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}

	  @EventHandler
	  public void onMove(PlayerMoveEvent event) {
		  Player player = event.getPlayer();
		  if(plugin.getConfig().getBoolean("DoubleJump")) {
			  if ((player.hasPermission("NE.doublejump") || player.isOp()) && (player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR))
			  {
				  event.getPlayer().setAllowFlight(true);
			  }  
		  }
	  }

	  	@EventHandler
	  	public void onFly(PlayerToggleFlightEvent event) {
	  		Player player = event.getPlayer();
	  		if(plugin.getConfig().getBoolean("DoubleJump")) {
	  			if ((player.hasPermission("NE.doublejump") || player.isOp()) && (player.getGameMode() != GameMode.CREATIVE)) {
	  		    	event.setCancelled(true);
	  		    	player.setAllowFlight(false);
	  		    	player.setFlying(false);
	  		    	player.setVelocity(player.getLocation().getDirection().multiply(0.2D).setY(0.5D));
	  		   	}
	  		}
	  }
}
