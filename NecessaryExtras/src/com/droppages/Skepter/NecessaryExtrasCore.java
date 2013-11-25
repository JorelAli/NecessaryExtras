package com.droppages.Skepter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

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

public class NecessaryExtrasCore extends JavaPlugin 
{
		
    Logger log = Logger.getLogger("Minecraft");
    protected UpdateChecker updateChecker;
    String pluginname = "NessaryExtras";
    public String prefix = null;
    public String text = null;
    public String SText = null;
    File configFile = new File(this.getDataFolder(), "config.yml");    
    
    public NecessaryExtrasCore()
    {
    }

    public void onEnable()
    {
    	try {
    	    Metrics metrics = new Metrics(this);
    	    metrics.start();
    	    log.info("[NecessaryExtras] " + pluginname + "Successfully hooked into Metrics");
    	    log.info("[NecessaryExtras] " + "This only sends small statistics about the server, but if you wish to opt out, you can adjust the settings in the PluginMetrics folder");
    	} catch (IOException e) {
    		log.warning("[NecessaryExtras] " + pluginname + " was unable to submit Metrics statistics");
    	}
    	PluginDescriptionFile description = getDescription();
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
        getServer().getPluginManager().registerEvents(new WaterListener(this), this);
        getServer().getPluginManager().registerEvents(new SwearListener(this), this);
        getServer().getPluginManager().registerEvents(new FakePluginListener(this), this);
        getServer().getPluginManager().registerEvents(new CodeChatListener(this), this);
        getServer().getPluginManager().registerEvents(new CapsListener(this), this);
        getServer().getPluginManager().registerEvents(new DoubleJumpListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new VoidListener(this), this);
        //if(configFile.exists()) {
        	//configurationUpdater(getConfig().getDouble("ConfigurationVersion"));
        //} else {
            saveDefaultConfig();
        //}
        colorSchemeSetup();
        log.info("[NecessaryExtras] " + pluginname + " " + description.getVersion() + " activated!");
        if(getConfig().getBoolean("UpdateCheck")) {
        	this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/necessaryextras/files.rss");
        	
        	if(this.updateChecker.updateNeeded()){
        		if(!(Double.parseDouble(this.updateChecker.getVersion()) <= Double.parseDouble(getDescription().getVersion()))) {
        			log.info("[NecessaryExtras] " + "A new version is available: " + this.updateChecker.getVersion());
            		log.info("[NecessaryExtras] " + "Get it from: " + this.updateChecker.getLink());
        		}
        	}
        }
    }

    public void onDisable()
    {
    	PluginDescriptionFile description = this.getDescription();
        log.info(pluginname + description.getVersion() + " de-activated!");
    }
    
    public void colorSchemeSetup() {
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
    
    public void configurationUpdater(double versionOfConfig) {
    	double version = versionOfConfig;
    	double pversion = Double.parseDouble(getDescription().getVersion());
    	if(version == pversion) {
    		return; //config is up to date
    	}
    	if(version < pversion) {
    		//get values
    		configFile.delete();
    		saveDefaultConfig();
    		//set values
    		//set config to updated state
    	}
    	
    }
}

    



  
