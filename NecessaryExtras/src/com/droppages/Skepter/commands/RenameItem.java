package com.droppages.Skepter.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import com.droppages.Skepter.NecessaryExtrasCore;

public class RenameItem implements CommandExecutor {

	public RenameItem(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
	}
	
	NecessaryExtrasCore plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player player = (Player) sender;
		if(sender.hasPermission("NE.rename") || sender.isOp()) {
			if(args.length == 0) {
				player.sendMessage(plugin.prefix + "Please enter a name for the item");
				return true;
			}
			if(command.getName().equalsIgnoreCase("Rename")) {
				ItemMeta im = player.getItemInHand().getItemMeta();
				StringBuilder msg = new StringBuilder(args[0]);
				for(int i = 1; i < args.length; i++) {
					msg.append(" ").append(args[i]);
				}
				String name = msg.toString();
				String m = ChatColor.translateAlternateColorCodes('&', name);
				im.setDisplayName(m);
				player.getItemInHand().setItemMeta(im);
				player.sendMessage(plugin.prefix + "Changed item name to " + m);
				return true;
			}
		}
		player.sendMessage(plugin.prefix + "You don't have permissions to use Rename");
		return true;
	}

}
