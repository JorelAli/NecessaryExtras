package com.droppages.Skepter.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.droppages.Skepter.NecessaryExtrasCore;

public class ForceChat implements CommandExecutor {

	NecessaryExtrasCore plugin;
	public ForceChat(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("NE.forcechat") || sender.isOp() || sender instanceof BlockCommandSender || sender instanceof ConsoleCommandSender) {
			if(command.getName().equalsIgnoreCase("ForceChat")) {
				if(args.length == 0) {
					sender.sendMessage(plugin.prefix + "Use " + plugin.SText + "/ForceChat <player> <message>");
					return true;
				} else if(args.length == 1) {
					sender.sendMessage(plugin.prefix + "Please enter a message");
					return true;
				} else if(args.length > 1) {
				
					Player target = null;
					try {
						target = Bukkit.getPlayerExact(args[0]);
					} catch (Exception e) {
						sender.sendMessage(plugin.prefix + "Player not found");
						return true;
					}
					StringBuilder msg = new StringBuilder(args[1]);
					for(int i = 2; i < args.length; i++) {
						msg.append(" ").append(args[i]);
					}
					String m = msg.toString();
					if(!(target.hasPermission("NEC.bypassforcechat")) || sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
						target.chat(m);
						return true;
					} else {
						sender.sendMessage(plugin.prefix + "You cannot Forcechat that player");
						return true;
					}
				}
			}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use forcechat");
		return true;
	}
}
