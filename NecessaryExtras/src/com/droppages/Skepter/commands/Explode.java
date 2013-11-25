package com.droppages.Skepter.commands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Explode implements CommandExecutor {

	public Explode(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}
	
	NecessaryExtrasCore plugin;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
    	Player player = (Player)sender;
		if(player.hasPermission("NE.explode") || player.isOp()) {
			if(command.getName().equalsIgnoreCase("Explode")) {
				Location loc = null;
				if(Arrays.asList(args).contains("-p")) {
						loc = player.getTargetBlock(null, 256).getLocation();
	    			} else {
	    				loc = player.getLocation();
	    			}
				if(args.length == 0) {
					player.sendMessage(plugin.prefix + "Please use /explode <size> <flags>");
					player.sendMessage(plugin.prefix + "Flags: -l = With lightning");
					player.sendMessage(plugin.prefix + "Flags: -p = Where you point");
					player.sendMessage(plugin.prefix + "Flags: -f = With fire");
					return true;
				}
		    	float explosionsize = Float.parseFloat(args[0]);
		    	if(args.length == 1) {
		    		if(explosionsize > 10){
	    				player.sendMessage(plugin.prefix + "Size of explosion 10 or less");
	    				return true;
	    			} else {
	    				player.getWorld().createExplosion(loc, explosionsize); 
	    			}
		    	}
		    	
				if(args.length == 2 && args[1].contains("-p")){
	    			if(explosionsize > 10){
	    				player.sendMessage(plugin.prefix + "Size of explosion 10 or less");
	    				return true;
	    			} else {
	    				player.getWorld().createExplosion(loc, explosionsize); 
	    			}
	    		} if(Arrays.asList(args).contains("-l")) {
	    			if(explosionsize > 10){
	    				player.sendMessage(plugin.prefix + "Size of explosion 10 or less");
	    				return true;
	    			} else {
	    				player.getWorld().createExplosion(loc, explosionsize); 
						player.getWorld().strikeLightning(loc);
	    			}
				} if(Arrays.asList(args).contains("-f")) {
					if(explosionsize > 10){
						player.sendMessage(plugin.prefix + "Size of explosion 10 or less");
						return true;
					} else {
						player.getWorld().createExplosion(loc, explosionsize, true); 
					}
				}
				return true;
			}
		}
		player.sendMessage(plugin.prefix + "You don't have permissions to use explode");
		return true;
	}
}
