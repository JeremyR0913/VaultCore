package me.vault.core.Events;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.vault.core.Main;
import me.vault.core.Objects.PluginPlayer;

public class JoinQuitEvents implements Listener {
	
	private Main plugin;
	
	public JoinQuitEvents(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void Death (PlayerDeathEvent e) {
		PluginPlayer p = plugin.getPlayerMap().get(e.getEntity().getUniqueId());
		if( p.getCredits() > 0) {
			int cred = p.getCredits() / 20;
			ItemStack s = new ItemStack(Material.EMERALD , cred );
			e.getDrops().add(s);
			p.addCredits(- cred);
		}
	}
	public void Pickup(PlayerPickupItemEvent e) {
		
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
