package com.droppages.Skepter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import com.droppages.Skepter.Updater.UpdateResult;
import com.droppages.Skepter.Updater.UpdateType;
import com.droppages.Skepter.commands.ClearChat;
import com.droppages.Skepter.commands.ConsoleCmd;
import com.droppages.Skepter.commands.ConsoleLog;
import com.droppages.Skepter.commands.Cook;
import com.droppages.Skepter.commands.Explode;
import com.droppages.Skepter.commands.FBroadcast;
import com.droppages.Skepter.commands.ForceChat;
import com.droppages.Skepter.commands.Freeze;
import com.droppages.Skepter.commands.Head;
import com.droppages.Skepter.commands.Log;
import com.droppages.Skepter.commands.MSpawner;
import com.droppages.Skepter.commands.NE;
import com.droppages.Skepter.commands.Oplist;
import com.droppages.Skepter.commands.RenameItem;
import com.droppages.Skepter.commands.See;
import com.droppages.Skepter.commands.SignEdit;
import com.droppages.Skepter.listeners.CapsListener;
import com.droppages.Skepter.listeners.CodeChatListener;
import com.droppages.Skepter.listeners.DeathListener;
import com.droppages.Skepter.listeners.DoubleJumpListener;
import com.droppages.Skepter.listeners.FakePluginListener;
import com.droppages.Skepter.listeners.SwearListener;
import com.droppages.Skepter.listeners.VoidListener;
import com.droppages.Skepter.listeners.WaterListener;

public class NecessaryExtrasCore extends JavaPlugin  {
		
	/*
	 * Version 3.2
	 * Just a SUPER QUICK update to fix up them F***ING signs all over the place!
	 * Features:
	 * Added section in config to disable death signs.
	 */
	
	/*
	 * Areas to work on:
	 * Support for color errors with ConsoleLog
	 * Fixing DeathListener
	 * Fixing See
	 * Fixing Freezing
	 * Exporting Permissions
	 * Export Commands to Cases
	 * Fixing Log
	 * Fixing RenameItem
	 */
	
	/*
	 * Features coming into version 4.0:
	 * SQLite database for storing player data 
	 * Auto updating support
	 */
	
	/*
	 * Things done:
	 * AutoUpdating
	 * Adding people to SQLite Database
	 */
	
    Logger log = Logger.getLogger("Minecraft");
//    private SQLite sqlite;
    String pluginname = "NecessaryExtras";
    public String prefix = null;
    public String text = null;
    public String SText = null;
    File configFile = new File(this.getDataFolder(), "config.yml");   

    public void onEnable() {
//    	File file = new File(getDataFolder(), "deathcountdown.db");
//		sqlite = new SQLite(file);
//		sqlite.open();
//		sqlite.execute("CREATE TABLE IF NOT EXISTS NE (playername VARCHAR(16), canSee BOOLEAN, isLogging BOOLEAN, isLoggingConsole BOOLEAN, isFrozen BOOLEAN);");
		UpdateAndMetrics();
    	registerCommandsAndEvents();
        
        
//      saveDefaultConfig();
        colorSchemeSetup();
    }
    
	public void onDisable() {
//       sqlite.close();
    }

    private void UpdateAndMetrics() {
    	if(getConfig().getBoolean("updateCheck")) {
			Updater updater = new Updater(this, 64701, this.getFile(), UpdateType.NO_DOWNLOAD, true);
			if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				this.getLogger().info("New version available! " + updater.getLatestName());
			}
		}
    	try {
    	    Metrics metrics = new Metrics(this);
    	    metrics.start();
    	    log.info("[NecessaryExtras] " + pluginname + "Successfully hooked into Metrics");
    	    log.info("[NecessaryExtras] " + "This only sends small statistics about the server, but if you wish to opt out, you can adjust the settings in the PluginMetrics folder");
    	} catch (IOException e) {
    		log.warning("[NecessaryExtras] " + pluginname + " was unable to submit Metrics statistics");
    	}   
	}

	private void registerCommandsAndEvents() {
    	getCommand("ForceChat").setExecutor(new ForceChat(this));
        getCommand("Explode").setExecutor(new Explode(this));
        getCommand("Oplist").setExecutor(new Oplist(this));
        getCommand("ConsoleCmd").setExecutor(new ConsoleCmd(this));
        getCommand("See").setExecutor(new See(this));
        getCommand("FBroadcast").setExecutor(new FBroadcast(this));
        getCommand("Rename").setExecutor(new RenameItem(this));
        getCommand("NE").setExecutor(new NE(this));
        getCommand("Log").setExecutor(new Log(this));
        getCommand("ConsoleLog").setExecutor(new ConsoleLog(this));
        getCommand("SignEdit").setExecutor(new SignEdit(this));
        getCommand("ClearChat").setExecutor(new ClearChat(this));
        getCommand("Head").setExecutor(new Head(this));
        getCommand("Cook").setExecutor(new Cook(this));
        getCommand("MSpawner").setExecutor(new MSpawner(this));
        getCommand("Freeze").setExecutor(new Freeze(this));
        
        /*
         * Commands to add:
         * ?
         */
        
        getServer().getPluginManager().registerEvents(new WaterListener(this), this);
        getServer().getPluginManager().registerEvents(new SwearListener(this), this);
        getServer().getPluginManager().registerEvents(new FakePluginListener(this), this);
        getServer().getPluginManager().registerEvents(new CodeChatListener(this), this);
        getServer().getPluginManager().registerEvents(new CapsListener(this), this);
        getServer().getPluginManager().registerEvents(new DoubleJumpListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new VoidListener(this), this);
//      getServer().getPluginManager().registerEvents(new JoinListener(this), this);
	}
    
    private void colorSchemeSetup() {
    	if(getConfig().getString("ColorScheme").equalsIgnoreCase("default")) {
    		prefix = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + pluginname + ChatColor.DARK_AQUA + "] " + ChatColor.GRAY;
    		text = ChatColor.GRAY + "";
    		SText = ChatColor.AQUA + "";
    		return;
    	} else if(getConfig().getString("ColorScheme").equalsIgnoreCase("red")) {
    		prefix = ChatColor.DARK_RED + "[" + ChatColor.RED + pluginname + ChatColor.DARK_RED + "] " + ChatColor.RED;
    		text = ChatColor.RED + "";
    		SText = ChatColor.DARK_RED + "";
    		return;
    	} else if(getConfig().getString("ColorScheme").equalsIgnoreCase("green")) {
    		prefix = ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + pluginname + ChatColor.DARK_GREEN + "] " + ChatColor.GREEN;
    		text = ChatColor.GREEN + "";
    		SText = ChatColor.DARK_GREEN + "";
    		return;
    	} else if(getConfig().getString("ColorScheme").equalsIgnoreCase("purple")) {
    		prefix = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + pluginname + ChatColor.DARK_PURPLE + "] " + ChatColor.LIGHT_PURPLE;
    		text = ChatColor.LIGHT_PURPLE + "";
    		SText = ChatColor.DARK_PURPLE + "";
    		return;
    	} else if(getConfig().getString("ColorScheme").equalsIgnoreCase("gold")) {
    		prefix = ChatColor.GOLD + "[" + ChatColor.YELLOW + pluginname + ChatColor.GOLD + "] " + ChatColor.YELLOW;
    		text = ChatColor.YELLOW + "";
    		SText = ChatColor.GOLD + "";
    		return;
    	}
    }
    
    
    /*
    
    //is(canSee, playername, player.getName());
    //SELECT canSee FROM NE WHERE playername = player.getName;
    public boolean is(String key, String where, String value) {
		ResultSet result = sqlite.executeQuery("SELECT " + key + " FROM NE WHERE " + where + "='" + value + "';");
		String r = sqlite.resultToString(result, key);

		return Boolean.valueOf(r);
    	//return true;
    }
    
    //Check if they're in there or not 
    public boolean check(Player player) {
		ResultSet result = sqlite.executeQuery("SELECT playername FROM NE;");
		ArrayList<String> r = sqlite.resultToArray(result, "playername");
		if(r == null || !(r.toString().contains(player.getName()))) {
			return false;
		} else {
			return true;
		}
	}
    
    public void Add(Player player) {
		sqlite.execute("INSERT INTO NE(playername, canSee, isLogging, isLoggingConsole, isFrozen) VALUES('" + player.getName() + "', 'false', 'false', 'false', 'false';");
		return;
    }
    */
}

    



  
