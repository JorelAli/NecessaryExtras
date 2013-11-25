package com.droppages.Skepter.commands;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.droppages.Skepter.NecessaryExtrasCore;

public class MSpawner implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public MSpawner(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		}

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player player = (Player)sender;
		if(player.hasPermission("NE.mspawner") || player.isOp()) {
			if(command.getName().equalsIgnoreCase("MSpawner")) {
				if(player.getTargetBlock(null, 256).equals(Material.MOB_SPAWNER)) {
					CreatureSpawner spawner = (CreatureSpawner) player.getTargetBlock(null, 256);
					if(args.length == 0){
						player.sendMessage(plugin.prefix + "Please enter a number for delay");
						return true;
					}
					if(args.length == 1){
						try {
							spawner.setDelay(Integer.parseInt(args[0]));
							return true;
						} catch (NumberFormatException e) {
							player.sendMessage(plugin.prefix + "That isn't a number!");
							return true;
						}
					}
					
				}
			}
			}
		player.sendMessage(plugin.prefix + "You don't have permissions to use MSpawner");
		return true;
		}
		
}
