package com.droppages.Skepter.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.droppages.Skepter.NecessaryExtrasCore;

public class See implements CommandExecutor, Listener {
	
	NecessaryExtrasCore plugin;
	public See(NecessaryExtrasCore plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		}

	public List<Player> players = new ArrayList<Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
    		sender.sendMessage(plugin.prefix + "You have to be a player to use this command");
    		return true;
    	}
		Player playerSender = (Player)sender;
		if(playerSender.hasPermission("NE.see") || playerSender.isOp()) {
			if(command.getName().equalsIgnoreCase("See") && args.length == 0) {
	    		if(players.contains(playerSender)) {
	    			players.remove(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are no longer using see");
	    			playerSender.removePotionEffect(PotionEffectType.NIGHT_VISION);
	    			return true;
	    		} else if(!players.contains(playerSender)){
	    			players.add(playerSender);
	    			playerSender.sendMessage(plugin.prefix + "You are now using see");
	    			playerSender.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 128000000, 1));
	    			return true;
	    		}
	    	} else if(command.getName().equalsIgnoreCase("See") && args.length == 1) {
	    		if(players.contains(Bukkit.getPlayerExact(args[0]))) {
	    			players.remove(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is no longer using see");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " disabled see for you");
	    			Bukkit.getPlayerExact(args[0]).removePotionEffect(PotionEffectType.NIGHT_VISION);
	    			return true;
	    		} else if(!players.contains(Bukkit.getPlayerExact(args[0]))){
	    			players.add(Bukkit.getPlayerExact(args[0]));
	    			playerSender.sendMessage(plugin.prefix + Bukkit.getPlayerExact(args[0]).getName() + " is now using see");
	    			Bukkit.getPlayerExact(args[0]).sendMessage(plugin.prefix + playerSender.getName() + " enabled see for you");
	    			Bukkit.getPlayerExact(args[0]).addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 128000000, 1));
	    			return true;
	    		}
	    	}

		}
		playerSender.sendMessage(plugin.prefix + "You don't have permissions to use See");
		return true;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		if(players.contains(event.getPlayer())) {
			players.remove(event.getPlayer());
			event.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
			return;
		}
	}
}
