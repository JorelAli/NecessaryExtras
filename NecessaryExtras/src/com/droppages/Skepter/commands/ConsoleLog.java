package com.droppages.Skepter.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class ConsoleLog implements CommandExecutor, Listener {

	NecessaryExtrasCore plugin;
	public List<Player> players = new ArrayList<Player>();

	public ConsoleLog(final NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	
		plugin.getServer().getLogger().addHandler(new Handler(){

			@Override
			public void publish(LogRecord record) {
				Iterator<Player> it = players.iterator();
				while(it.hasNext()) {
					Player player = it.next();
					if(players.contains(player)) {
						player.sendMessage(plugin.SText + "[ConsoleLog] " + plugin.text + record.getMessage());
					}
					return;
				}
				
			}

			@Override
			public void flush() {
			}

			@Override
			public void close() throws SecurityException {
			}
		});
	}
     
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
    	Player playerSender = (Player)sender;
		if(playerSender.hasPermission("NE.consolelog") || playerSender.isOp()) {
			if(command.getName().equalsIgnoreCase("ConsoleLog") && args.length == 0) {
	    		if(players.contains(playerSender)) {
	    			players.remove(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are no longer viewing the Console Log");
	    			return true;
	    		} else if(!players.contains(playerSender)){
	    			players.add(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are now viewing the Console Log");
	    			return true;
	    		}
			} else if(command.getName().equalsIgnoreCase("ConsoleLog") && args[0].equalsIgnoreCase("list")) {
				playerSender.sendMessage(plugin.prefix + "Players using ConsoleLog:");
	    		for(Player p : players) {
	    			playerSender.sendMessage(plugin.text + p.getName());
	    		}
		    		return true;
	    	} else if(command.getName().equalsIgnoreCase("ConsoleLog") && args.length == 1) {
	    		if(players.contains(Bukkit.getPlayerExact(args[0]))) {
	    			players.remove(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is no longer viewing the Console Log");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " disabled the Console Log for you");
	    			return true;
	    		} else if(!players.contains(Bukkit.getPlayerExact(args[0]))){
	    			players.add(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is now viewing the Console Log");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " enabled the Console Log for you");
	    			return true;
	    		}
	    	}
		}
		playerSender.sendMessage(plugin.prefix + "You don't have permissions to use ConsoleLog");
		return true;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		if(players.contains(event.getPlayer())) {
			players.remove(event.getPlayer());
			return;
		}
	}

}
