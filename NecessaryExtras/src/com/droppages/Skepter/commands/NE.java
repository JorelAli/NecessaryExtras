package com.droppages.Skepter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.droppages.Skepter.NecessaryExtrasCore;

public class NE implements CommandExecutor {

	public NE(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}
	
	NecessaryExtrasCore plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("NE.ne") || sender.isOp() || sender instanceof ConsoleCommandSender) {
			if(command.getName().equalsIgnoreCase("NE") && args.length == 0) {
				sender.sendMessage(plugin.prefix + "NecessaryExtras v" + plugin.getDescription().getVersion() + " created by Skepter");
				sender.sendMessage(plugin.prefix + "Use /NE commands to see the list of commands");
				sender.sendMessage(plugin.prefix + "Use /NE permissions to see the list of permissions");
				return true;
			}
			if(command.getName().equalsIgnoreCase("NE") && args[0].equalsIgnoreCase("commands")) {
				sender.sendMessage(plugin.prefix + "Available commands:");
				sender.sendMessage(plugin.SText + "/Oplist" + plugin.text + " - See a list of Operators");
				sender.sendMessage(plugin.SText + "/ForceChat" + plugin.text + " - Force a player to say something");
				sender.sendMessage(plugin.SText + "/Explode" + plugin.text + " - Create an explosion of a defined size");
				sender.sendMessage(plugin.SText + "/ConsoleCmd" + plugin.text + " - Run a command as the console");
				sender.sendMessage(plugin.SText + "/ConsoleLog" + plugin.text + " - View the log from the console");
				sender.sendMessage(plugin.SText + "/See" + plugin.text + " - See in the dark");
				sender.sendMessage(plugin.SText + "/FBroadcast" + plugin.text + " - Broadcast a message without a prefix");
				sender.sendMessage(plugin.SText + "/Rename" + plugin.text + " - Rename an item in your hand");
				sender.sendMessage(plugin.SText + "/Log" + plugin.text + " - Logs commands a player uses");
				sender.sendMessage(plugin.SText + "/SignEdit" + plugin.text + " - Allows you to change text on a sign");
				sender.sendMessage(plugin.SText + "/ClearChat" + plugin.text + " - Clears the server chat");
				sender.sendMessage(plugin.SText + "/Head" + plugin.text + " - Spawn a player's head");
				sender.sendMessage(plugin.SText + "/MSpawner" + plugin.text + " - Adjust a mobspawner's delay between spawning");
				sender.sendMessage(plugin.SText + "/Cook" + plugin.text + " - Cook food in your hand");
				sender.sendMessage(plugin.SText + "/NE reload" + plugin.text + " - Reloads the configuration");
				sender.sendMessage(plugin.SText + "/NE" + plugin.text + " - Display this list");
				return true;
			}
				else if(command.getName().equalsIgnoreCase("NE") && args[0].equalsIgnoreCase("permissions")) {
				sender.sendMessage(plugin.prefix + "Available permissions:");
				sender.sendMessage(plugin.SText + "NE.oplist" + plugin.text + " - Allows the player to use Oplist");
				sender.sendMessage(plugin.SText + "NE.forcechat" + plugin.text + " - Allows the player to use ForceChat");
				sender.sendMessage(plugin.SText + "NE.explode" + plugin.text + " - Allows the player to use Explode");
				sender.sendMessage(plugin.SText + "NE.consolecmd" + plugin.text + " - Allows the player to use ConsoleCmd");
				sender.sendMessage(plugin.SText + "NE.consolelog" + plugin.text + " - View the log from the console");
				sender.sendMessage(plugin.SText + "NE.see" + plugin.text + " - Allows the player to use See");
				sender.sendMessage(plugin.SText + "NE.fbroadcast" + plugin.text + " - Allows the player to use FBroadcast");
				sender.sendMessage(plugin.SText + "NE.rename" + plugin.text + " - Allows the player to use Rename");
				sender.sendMessage(plugin.SText + "NE.log" + plugin.text + " - Allows the player to use Log");
				sender.sendMessage(plugin.SText + "NE.signedit" + plugin.text + " - Allows the player to use SignEdit");
				sender.sendMessage(plugin.SText + "NE.clearchat" + plugin.text + " - Allows the player to use ClearChat");
				sender.sendMessage(plugin.SText + "NE.head" + plugin.text + " - Allows the player to use Head");
				sender.sendMessage(plugin.SText + "NE.mspawner" + plugin.text + " - Allows the player to use MSpawner");
				sender.sendMessage(plugin.SText + "NE.cook" + plugin.text + " - Allows the player to use Cook");
				sender.sendMessage(plugin.SText + "NE.ne" + plugin.text + " - Allows the player to use ne");
				sender.sendMessage("");
				sender.sendMessage(plugin.SText + "NE.bypassswear" + plugin.text + " - Allows the player to bypass swearing");
				sender.sendMessage(plugin.SText + "NE.bypassplugins" + plugin.text + " - Allows the player to bypass the fake plugin list");
				sender.sendMessage(plugin.SText + "NE.bypasscaps" + plugin.text + " - Allows the player to bypass using lots of caps");
				sender.sendMessage(plugin.SText + "NE.bypassforcechat" + plugin.text + " - Allows the player to bypass being forced to chat");
                return true;
			}
				else if(command.getName().equalsIgnoreCase("NE") && args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					sender.sendMessage(plugin.prefix + "Config Reloaded!");
	                return true;
			}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use NE");
		return true;
	}

}
