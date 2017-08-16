package me.phil.vote;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class HC extends JavaPlugin implements Listener {
	
	ArrayList<String> off_votes = new ArrayList<String>();
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(off_votes.contains(e.getPlayer().getName().toLowerCase())) {
			Bukkit.broadcastMessage("§cPlayMore §8» §7Der Spieler §c"+e.getPlayer().getName()+" §7hat für uns gevotet §8(§4/vote§8)");
			ItemStack it = new ItemStack(Material.DIAMOND, 3);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName("§cPlayMore §8» §c/vote");
			it.setItemMeta(itm);
			
			e.getPlayer().getInventory().addItem(it);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EXPLODE, 5.0F, 5.0F);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
		}
	}
	
	@EventHandler
	public void onVote(com.vexsoftware.votifier.model.VotifierEvent e) {
		if(Bukkit.getPlayer(e.getVote().getUsername()) != null) {
			Bukkit.broadcastMessage("§cPlayMore §8» §7Der Spieler §c"+e.getVote().getUsername()+" §7hat für uns gevotet §8(§4/vote§8)");
			ItemStack it = new ItemStack(Material.DIAMOND, 3);
			ItemMeta itm = it.getItemMeta();
			itm.setDisplayName("§cPlayMore §8» §c/vote");
			it.setItemMeta(itm);
			
			Bukkit.getPlayer(e.getVote().getUsername()).getInventory().addItem(it);
			Bukkit.getPlayer(e.getVote().getUsername()).playSound(Bukkit.getPlayer(e.getVote().getUsername()).getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
			Bukkit.getPlayer(e.getVote().getUsername()).playSound(Bukkit.getPlayer(e.getVote().getUsername()).getLocation(), Sound.EXPLODE, 5.0F, 5.0F);
			Bukkit.getPlayer(e.getVote().getUsername()).playSound(Bukkit.getPlayer(e.getVote().getUsername()).getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			return;
		}else {
			off_votes.add(e.getVote().getUsername().toLowerCase());
			return;
		}
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		Player p = (Player)sender;
		if(cmd.equalsIgnoreCase("vote")) {
			if(args.length == 0) {
			p.sendMessage("§cPlayMore §8»§e https://minecraft-server.eu/vote/index/126209 §8(§4Klick§8)");
			p.sendMessage("§cPlayMore §8» §7Belohnung §8- §b3x Diamanten");
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			return true;
			}
			if(Bukkit.getPlayer(args[0]) != null && p.hasPermission("vote.test")) {
				Bukkit.broadcastMessage("§cPlayMore §8» §7Der Spieler §c"+p.getName()+" §7hat für uns gevotet §8(§4/vote§8)");
				ItemStack it = new ItemStack(Material.DIAMOND, 3);
				ItemMeta itm = it.getItemMeta();
				itm.setDisplayName("§cPlayMore §8» §c/vote");
				it.setItemMeta(itm);
				
				p.getInventory().addItem(it);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				p.playSound(p.getLocation(), Sound.EXPLODE, 5.0F, 5.0F);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			}else {
				p.sendMessage("§cPlayMore §8» §7Spieler ist nicht online!");
			}
			
			
		}
		
		return false;
        
        
}

}
