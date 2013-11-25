package com.droppages.Skepter.commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Oplist implements CommandExecutor {

	public Oplist(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}
	
	NecessaryExtrasCore plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("NE.oplist") || sender.isOp() || sender instanceof ConsoleCommandSender) {
			if(command.getName().equalsIgnoreCase("Oplist")) {
				Set<OfflinePlayer> ops = Bukkit.getOperators();
				if(ops.size() == 1) {
					sender.sendMessage(plugin.prefix + "There is " + ops.size() + " operator:");
				} else if(ops.size() == 0) {
					sender.sendMessage(plugin.prefix + "There are no operators.");
					return true;
				} else {
				sender.sendMessage(plugin.prefix + "There are " + ops.size() + " operators:");
				}
                for (OfflinePlayer s : ops) {
                	if(s.toString().contains("CraftPlayer")) {
                		String onlinePlayerName = s.toString().substring(17, s.toString().length() -1);
                		String FormattedName = ChatColor.GREEN + "[Online] " + ChatColor.WHITE + onlinePlayerName;
                		sender.sendMessage(FormattedName);
                	}
                	else if(s.toString().contains("CraftOfflinePlayer")) {
                		String offlinePlayerName = s.toString().substring(24, s.toString().length() -1);
                		String FormattedName = ChatColor.RED + "[Offline] " + ChatColor.WHITE + offlinePlayerName;
                		sender.sendMessage(FormattedName);
                	}
                }
                return true;
			}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use Oplist");
		return true;
	}

}
