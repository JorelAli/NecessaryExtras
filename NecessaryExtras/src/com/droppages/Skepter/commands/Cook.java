package com.droppages.Skepter.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.droppages.Skepter.NecessaryExtrasCore;

public class Cook implements CommandExecutor {
 
	NecessaryExtrasCore plugin;
	public Cook(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player player = (Player) sender;
		if(sender.hasPermission("NE.cook") || sender.isOp()) {
			if(command.getName().equalsIgnoreCase("Cook")) {
				ItemStack item = player.getItemInHand();
				if(item.isSimilar(new ItemStack(Material.RAW_BEEF))) {
					int amount = item.getAmount();
					ItemStack newitem = new ItemStack(Material.COOKED_BEEF, amount);
					player.setItemInHand(newitem);
					player.sendMessage(plugin.prefix + "Cooked " + amount + " beef");
					return true;
				} else if(item.isSimilar(new ItemStack(Material.RAW_CHICKEN))) {
					int amount = item.getAmount();
					ItemStack newitem = new ItemStack(Material.COOKED_CHICKEN, amount);
					player.setItemInHand(newitem);
					player.sendMessage(plugin.prefix + "Cooked " + amount + " chicken");
					return true;
				} else if(item.isSimilar(new ItemStack(Material.RAW_FISH))) {
					int amount = item.getAmount();
					ItemStack newitem = new ItemStack(Material.COOKED_FISH, amount);
					player.setItemInHand(newitem);
					player.sendMessage(plugin.prefix + "Cooked " + amount + " fish");
					return true;
				} else if(item.isSimilar(new ItemStack(Material.POTATO_ITEM))) {
					int amount = item.getAmount();
					ItemStack newitem = new ItemStack(Material.BAKED_POTATO, amount);
					player.setItemInHand(newitem);
					player.sendMessage(plugin.prefix + "Cooked " + amount + " potato");
					return true;
				} else if(item.isSimilar(new ItemStack(Material.PORK))) {
					int amount = item.getAmount();
					ItemStack newitem = new ItemStack(Material.GRILLED_PORK, amount);
					player.setItemInHand(newitem);
					player.sendMessage(plugin.prefix + "Cooked " + amount + " pork");
					return true;
				} else {
					player.sendMessage(plugin.prefix + "Couldn't cook " + item.getType().toString().toLowerCase().replaceAll("_", " "));
					return true;
				}
			}
		}
		sender.sendMessage(plugin.prefix + "You don't have permissions to use cook");
		return true;
	}
}
