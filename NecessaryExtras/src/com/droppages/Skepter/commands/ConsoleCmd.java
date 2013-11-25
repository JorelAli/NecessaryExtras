package com.droppages.Skepter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.droppages.Skepter.NecessaryExtrasCore;

public class ConsoleCmd implements CommandExecutor {

	NecessaryExtrasCore plugin;
	public ConsoleCmd(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
    	Player player = (Player)sender;
    	if (plugin.getConfig().getBoolean("ConsoleCmdOps") == true) {
    		if(player.isOp()) {
    			if(command.getName().equalsIgnoreCase("ConsoleCmd")) {
    				if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
    					player.sendMessage(plugin.prefix + "Use " + plugin.SText + "/ConsoleCmd <command>");
    					return true;
    	    		} else {
    	    			StringBuilder msg = new StringBuilder(args[0]);
    					for(int i = 1; i < args.length; i++) {
    						msg.append(" ").append(args[i]);
    					}
    	    			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg.toString());
    					return true;
    	    		}
    			}
    		}
    	} else {
    		if(player.hasPermission("NE.consolecmd") && !player.isOp()) {
    			if(command.getName().equalsIgnoreCase("ConsoleCmd")) {
    				if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
    					player.sendMessage(plugin.prefix + "Use " + plugin.SText + "/ConsoleCmd <command>");
    					return true;
    				} else {
    					StringBuilder msg = new StringBuilder(args[0]);
    					for(int i = 1; i < args.length; i++) {
    						msg.append(" ").append(args[i]);
    					}
    					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg.toString());
    					return true;
    				}
    			}
    		}
    		player.sendMessage(plugin.prefix + "You don't have permissions to use ConsoleCmd");
    		return true;
    	}
		return false;
	}
}
