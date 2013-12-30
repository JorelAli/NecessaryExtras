package com.droppages.Skepter.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class SignEdit implements CommandExecutor {
	
	NecessaryExtrasCore plugin;
	public SignEdit(NecessaryExtrasCore plugin) {
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
		if(player.hasPermission("NE.signedit") || player.isOp()) {
			if(command.getName().equalsIgnoreCase("SignEdit")) {
				
				if(args.length == 0 || args.length == 1) {
					player.sendMessage(plugin.prefix + "Please use /SignEdit <Line number> <Text>");
					return true;
				}
				
				StringBuilder msg = new StringBuilder(args[1]);
				for(int i = 2; i < args.length; i++) {
					msg.append(" ").append(args[i]);
				}
				String m = msg.toString();
				String m1 = ChatColor.translateAlternateColorCodes('&', m);
				String m2 = m1.replace("[empty]", "");
				if(player.getTargetBlock(null, 256).getState() instanceof Sign) {
					Sign sign = (Sign) player.getTargetBlock(null, 256).getState();
					try {
						if(Integer.parseInt(args[0]) > 4) {
							player.sendMessage(plugin.prefix + "Line doesn't exist");
							return true;
						}
						BlockPlaceEvent event1 = new BlockPlaceEvent(player.getTargetBlock(null, 256), sign, player.getTargetBlock(null, 256).getRelative(BlockFace.SELF), player.getItemInHand(), player, true);
						Bukkit.getServer().getPluginManager().callEvent(event1);
						sign.setLine(Integer.parseInt(args[0]) -1, m2);
						sign.update(true);
						return true;
					} catch (NumberFormatException e) {
						player.sendMessage(plugin.prefix + "Please enter a line number");
						return true;
					}
				} else {
					player.sendMessage(plugin.prefix + "Couldn't find a sign!");
					return true;
				}
	    	}
		}
		player.sendMessage(plugin.prefix + "You don't have permissions to use SignEdit");
		return true;
	}
}
