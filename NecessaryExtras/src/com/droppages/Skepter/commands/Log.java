package com.droppages.Skepter.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Log implements CommandExecutor, Listener {
	
	NecessaryExtrasCore plugin;
	public Log(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}

	public List<Player> players = new ArrayList<Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player playerSender = (Player)sender;
		if(playerSender.hasPermission("NE.log") || playerSender.isOp()) {
			if(command.getName().equalsIgnoreCase("Log") && args.length == 0) {
	    		if(players.contains(playerSender)) {
	    			players.remove(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are no longer viewing the Command Log");
	    			return true;
	    		} else if(!players.contains(playerSender)){
	    			players.add(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are now viewing the Command Log");
	    			return true;
	    		}
	    	} else if(command.getName().equalsIgnoreCase("Log") && args[0].equalsIgnoreCase("list")) {
	    		playerSender.sendMessage(plugin.prefix + "Players using Log:");
	    		for(Player p : players) {
	    			playerSender.sendMessage(plugin.text + p.getName());
	    		}
	    		return true;
	    	} else if(command.getName().equalsIgnoreCase("Log") && args.length == 1) {
	    		if(players.contains(Bukkit.getPlayerExact(args[0]))) {
	    			players.remove(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is no longer viewing the Command Log");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " disabled the Command Log for you");
	    			return true;
	    		} else if(!players.contains(Bukkit.getPlayerExact(args[0]))){
	    			players.add(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is now viewing the Command Log");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " enabled the Command Log for you");
	    			return true;
	    		}
	    	}
			
		}
		playerSender.sendMessage(plugin.prefix + "You don't have permissions to use Log");
		return true;
	}
	
	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent event) {
		if(!event.isCancelled()) {
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player player = it.next();
				player.sendMessage(plugin.prefix + plugin.SText + event.getPlayer().getName() + plugin.text +  " used " + plugin.SText + event.getMessage());
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		if(players.contains(event.getPlayer())) {
			players.remove(event.getPlayer());
			return;
		}
	}
}
