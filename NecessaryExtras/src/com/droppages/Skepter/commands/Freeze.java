package com.droppages.Skepter.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Freeze implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public Freeze(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		}

	public List<Player> frozen = new ArrayList<Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("NE.freeze") || sender.isOp() || sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
			if(command.getName().equalsIgnoreCase("Freeze")) {
				if(args.length == 0) {
					sender.sendMessage(plugin.prefix + "Sorry guys, I'm working on this as quick as possible - Skepter (NE Developer)");
					return false;
				}
				if (args.length == 1) {
					return false;
				//add player to list
				}
	    	}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use Freeze");
		return true;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		//if they move and are in the arraylist
		//CANCEL :)
		//check Y moving axis
		//block breaking
		//recieve/send damage
	}
}
