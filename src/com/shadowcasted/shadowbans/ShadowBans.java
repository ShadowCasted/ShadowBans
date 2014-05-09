package com.shadowcasted.shadowbans;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.shadowcasted.shadowbans.commands.Ban;
import com.shadowcasted.shadowbans.commands.CheckBan;
import com.shadowcasted.shadowbans.commands.TempBan;
import com.shadowcasted.shadowbans.commands.Unban;
import com.shadowcasted.shadowbans.commands.Uuid;
import com.shadowcasted.shadowbans.config.sconfig;

public class ShadowBans extends JavaPlugin {
	public static ShadowBans plugin;
	public static sconfig sconfig;

	public ShadowBans() {
		ShadowBans.sconfig = new sconfig(this);
	}

	@Override
	public void onEnable() {
		setup();
		getServer().getLogger().info("[ShadowBans] is now enabled.");
	}

	@Override
	public void onDisable() {
		getServer().getLogger().info("[ShadowBans] is now disabled.");
	}

	void setup() {
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("uuid").setExecutor(new Uuid(this));
		getCommand("tempban").setExecutor(new TempBan(this));
		getCommand("unban").setExecutor(new Unban(this));
		getCommand("checkban").setExecutor(new CheckBan(this));
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onPlayerJoin (PlayerLoginEvent event) {
		if (ShadowBans.sconfig.getConfig().isSet("bans." + event.getPlayer().getUniqueId().toString())) {
			long unix = System.currentTimeMillis() / 1000;
			if (ShadowBans.sconfig.getConfig().isSet("ends." + event.getPlayer().getUniqueId().toString()) && ShadowBans.sconfig.getConfig().getInt("ends." + event.getPlayer().getUniqueId().toString()) < unix) {
				event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
				String message = ShadowBans.sconfig.getConfig().getString("bans." + event.getPlayer().getUniqueId().toString());
				event.setKickMessage(ChatColor.translateAlternateColorCodes('&', message));
			} else if (ShadowBans.sconfig.getConfig().isSet("ends." + event.getPlayer().getUniqueId().toString())) {
				ShadowBans.sconfig.getConfig().set("ends." + event.getPlayer().getUniqueId().toString(), null);
			}
		}
	}
}