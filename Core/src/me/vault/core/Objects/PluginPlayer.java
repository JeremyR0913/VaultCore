package me.vault.core.Objects;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.vault.core.Main;

public class PluginPlayer {
	
	private Main plugin;
	private Player player;
	private String username;
	private UUID uniqueID;
	private File file;
	private FileConfiguration fileConfig;
	
	/**
	 * Blah blah blah
	 * @param player
	 * @param plugin
	 */
	
	public PluginPlayer(Player player, Main plugin) {
		this.plugin = plugin;
		this.player = player;
		this.username = player.getName();
		this.uniqueID = player.getUniqueId();
		this.file = new File(plugin.getDataFolder() + File.separator + "userdata", this.uniqueID + ".yml");
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.fileConfig = YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * This is blah blah
	 */
	public void removal() {
		try {
			this.fileConfig.save(file);
		} catch (IOException e) {
			plugin.log("ERR: Failed to save userdata for " + username + ". (" + this.uniqueID + ".yml)");
			e.printStackTrace();
		}
	}
	
	public int getCredits() {
		return this.fileConfig.getInt("credits");
	}
	
	public void setCredits(int credits) {
		this.fileConfig.set("credits", credits);
	}
	
	public void addCredits(int credits) {
		this.fileConfig.set("credits", getCredits() + credits);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getUsername() {
		return username;
	}
	
	public UUID getUniqueID() {
		return uniqueID;
	}

}
