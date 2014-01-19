package com.droppages.Skepter.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class DeathListener implements Listener {

	NecessaryExtrasCore plugin;
	public DeathListener(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) { 
		if(plugin.getConfig().getBoolean("DeathSigns")) {
			Player player = event.getEntity();
			Location loc = player.getLocation();
			loc.getBlock().setType(Material.AIR);
			loc.getBlock().setType(Material.SIGN_POST);
			Sign sign = (Sign) loc.getBlock().getState();
			sign.setLine(0, player.getName());
			sign.setLine(1, "Died here on");
			sign.setLine(2, new SimpleDateFormat("MMM W").format(new Date()));
			sign.setLine(3, new SimpleDateFormat("hh:mm a").format(new Date()));
			sign.update();
		}
		return;
	}
}
