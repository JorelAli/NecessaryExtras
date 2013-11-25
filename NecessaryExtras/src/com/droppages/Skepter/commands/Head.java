package com.droppages.Skepter.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Head implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public Head(NecessaryExtrasCore plugin) {
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
		if(player.hasPermission("NE.head") || player.isOp()) {
			if(command.getName().equalsIgnoreCase("Head")) {
				if(args.length == 0) {
					player.sendMessage(plugin.prefix + "Please enter a username");
					return true;
				}
				if (args.length == 1) {
				ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta skull = (SkullMeta) head.getItemMeta();
				skull.setOwner(args[0]);
				head.setItemMeta(skull);
				player.getInventory().addItem(head);
				player.updateInventory();
				return true;
				}
	    	}
		}
		player.sendMessage(plugin.prefix + "You don't have permissions to use SignEdit");
		return true;
	}
}
