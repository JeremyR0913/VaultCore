package me.vault.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.vault.core.Events.JoinQuitEvents;
import me.vault.core.Objects.PluginPlayer;

public class Main  extends JavaPlugin{
	
	public void onDisable() {
		for (PluginPlayer pluginPlayer : this.playerMap.values()) {
			pluginPlayer.removal();
		}
	}
	private String mainPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Vault" 
			+ ChatColor.DARK_GRAY + "]: " + ChatColor.GRAY;
	
	public String getMainPrefix() {
		return mainPrefix;
	}
	
	public void onEnable() {
		registerFolders();
		registerFiles();
		registerConfig();
		registerPlayers();
		registerEvents();
		registerCommands();
	}

	private void registerConfig() {
		// TODO Auto-generated method stub
		
	}

	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuitEvents(this), this);
	}

	private void registerCommands() {
		// TODO Auto-generated method stub
		
	}
	
	private Map<UUID, PluginPlayer> playerMap;

	private void registerPlayers() {
		this.playerMap = new HashMap<UUID, PluginPlayer>();
		for (Player player: Bukkit.getOnlinePlayers()) {
			this.playerMap.put(player.getUniqueId(), new PluginPlayer(player, this));
		}
	}

	private void registerFolders() {
		File userdataFolder = new File(this.getDataFolder() + File.separator + "userdata");
		if (!userdataFolder.exists()) {
			this.log("Generating userdata folder...");
			userdataFolder.mkdirs();
		}
	}

	private void registerFiles() {
		File language = new File(this.getDataFolder(), "language.yml");
		if (!language.exists()) {
			this.log("Generating language.yml...");
			try {
				language.createNewFile();
			} catch (IOException e) {
				this.log("ERR: Failed to create language.yml...");
				e.printStackTrace();
			}
			FileConfiguration languageConfig = YamlConfiguration.loadConfiguration(language);
			// TODO generate neccessary messages
			try {
				languageConfig.save(language);
			} catch (IOException e) {
				this.log("ERR: Failed to save language.yml...");
				e.printStackTrace();
			}
		}
	}
	
	public Map<UUID, PluginPlayer> getPlayerMap() {
		return playerMap;
	}
	
	public String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public void debug(String string) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("vault.messages.debug")) {
				player.sendMessage("[Vault] " + string);
			}
		}
	}

	public void log(String string) {
		System.out.println("[Vault] " + string);
	}
}
