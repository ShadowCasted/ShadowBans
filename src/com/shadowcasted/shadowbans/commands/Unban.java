package com.shadowcasted.shadowbans.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shadowcasted.shadowbans.ShadowBans;

public class Unban implements CommandExecutor {
	final ShadowBans plugin;

	public Unban(ShadowBans instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("ShadowBans.unban")) {
			if (args.length == 1) {
				UUID uuid = null;
				if (args[0].matches("[0-9a-zA-Z-]{36}")) {
					if (Bukkit.getPlayer(UUID.fromString(args[0])).isValid()) {
						uuid = UUID.fromString(args[0]);
					}
				}
				if (uuid != null) {
					if (ShadowBans.sconfig.getConfig().isSet("bans." + uuid.toString())) {
						ShadowBans.sconfig.getConfig().set("bans." + uuid.toString(), null);
						if (ShadowBans.sconfig.getConfig().isSet("ends." + uuid.toString())) {
							ShadowBans.sconfig.getConfig().set("ends." + uuid.toString(), null);
							Bukkit.broadcast("Player " + uuid + " has been unbanned", null);
						}
	
						ShadowBans.sconfig.saveConfig();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "First argument must be a valid UUID.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Error with arguments : /" + label + " <player/UUID>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have the permissions!");
		}

		return true;
	}
}