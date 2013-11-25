package com.droppages.Skepter.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.droppages.Skepter.NecessaryExtrasCore;

public class FakePluginListener implements Listener {

	NecessaryExtrasCore plugin;
	public FakePluginListener(NecessaryExtrasCore plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onCommandPreEnter(PlayerCommandPreprocessEvent event) {
		if(plugin.getConfig().getBoolean("EnableFakePluginList") == false) {
			return;
		} else {
			if(event.getMessage().equalsIgnoreCase("/plugins") || event.getMessage().equalsIgnoreCase("/pl")) {
				if(!event.getPlayer().hasPermission("NE.bypassplugins") || !event.getPlayer().isOp()) {
					event.setCancelled(true);
					List<String> args = new ArrayList<String>(plugin.getConfig().getStringList("FakePlugins"));
					Object[] arr = args.toArray();
					StringBuilder msg = new StringBuilder();
					for(int i = 0; i < arr.length; i++) {
						msg.append(ChatColor.GREEN).append(arr[i]).append(ChatColor.WHITE).append(", ");
					}
					String m1 = msg.substring(0, msg.length() -2);
	    			String prefix = "Plugins (" + arr.length + "): ";
					event.getPlayer().sendMessage(prefix + m1);
	    			return;
				} else {
					return;
				}
			}
		}
	}
}
