package com.droppages.Skepter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.droppages.Skepter.NecessaryExtrasCore;

public class ClearChat implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public ClearChat(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		}

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player || sender instanceof BlockCommandSender)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player player = (Player)sender;
		if(player.hasPermission("NE.clearchat") || player.isOp() || sender instanceof BlockCommandSender) {
			if(command.getName().equalsIgnoreCase("ClearChat") && args.length == 0) {
				for(int i = 0; i < 120; i++) {
					player.sendMessage("");
				}
				return true;
	    	} else if (command.getName().equalsIgnoreCase("ClearChat") && args[0].equalsIgnoreCase("all")) {
	    		for(int i = 0; i < 120; i++) {
					Bukkit.broadcastMessage("");
				}
				return true;
	    	} else if (command.getName().equalsIgnoreCase("ClearChat") && args.length == 1) {
	    		Player target;
	    		try {
					target = (Player) Bukkit.getPlayerExact(args[0]);
	    		} catch (Exception e) {
					player.sendMessage(plugin.prefix + "Couldn't find player!");
					return true;
				}
					for(int i = 0; i < 120; i++) {
						target.sendMessage("");
					}
					return true;
	    	}
		}
		player.sendMessage(plugin.prefix + "You don't have permissions to use ClearChat");
		return true;
	}
}
