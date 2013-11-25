package com.droppages.Skepter.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.droppages.Skepter.NecessaryExtrasCore;

public class FBroadcast implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public FBroadcast(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("NEC.fbroadcast") || sender.isOp() || sender instanceof BlockCommandSender || sender instanceof ConsoleCommandSender) {
			if(command.getName().equalsIgnoreCase("FBroadcast")) {
				if(args.length == 0) {
					sender.sendMessage(plugin.prefix + "Use " + plugin.SText + "/FBroadcast <message>");
					return true;
	    		} else {
	    			StringBuilder msg = new StringBuilder(args[0]);
					for(int i = 1; i < args.length; i++) {
						msg.append(" ").append(args[i]);
					}
					String m = msg.toString();
					String m1 = ChatColor.translateAlternateColorCodes('&', m);
	    			Bukkit.broadcastMessage(m1);
	    			return true;
	    		}
			}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use FBroadcast");
		return true;
	}
}
