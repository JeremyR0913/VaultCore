package me.vault.core.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.vault.core.Main;
import me.vault.core.Objects.PluginPlayer;

public class JoinQuitEvents implements Listener {
	
	private Main plugin;
	
	public JoinQuitEvents(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent e) {
		plugin.getPlayerMap().put(e.getPlayer().getUniqueId(), new PluginPlayer(e.getPlayer(), plugin));
	}

	@EventHandler
	public void quitEvent(PlayerQuitEvent e) {
		PluginPlayer pluginPlayer = plugin.getPlayerMap().get(e.getPlayer().getUniqueId());
		
		
		
		
		pluginPlayer.removal();
		plugin.getPlayerMap().remove(e.getPlayer().getUniqueId());
	}

}
