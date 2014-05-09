package com.shadowcasted.shadowbans.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shadowcasted.shadowbans.ShadowBans;

public class CheckBan implements CommandExecutor {
	final ShadowBans plugin;

	public CheckBan(ShadowBans instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("ShadowBans.check")) {
			if (args.length == 1) {
				long unix = System.currentTimeMillis() / 1000;
				UUID uuid = null;
				if (args[0].matches("[0-9a-zA-Z-]{36}")) {
					if (Bukkit.getPlayer(UUID.fromString(args[0])).isValid()) {
						uuid = UUID.fromString(args[0]);
					}
				}
				if (uuid != null) {
					if (ShadowBans.sconfig.getConfig().isSet("bans." + uuid.toString())) {
						sender.sendMessage(ChatColor.GREEN + args[0] + " is banned for :");
						sender.sendMessage(ShadowBans.sconfig.getConfig().getString("bans." + uuid.toString()));
						if (ShadowBans.sconfig.getConfig().isSet("ends." + uuid.toString())) {
							sender.sendMessage(ChatColor.GREEN + "He will be unbanned in " + (unix-ShadowBans.sconfig.getConfig().getInt("ends." + uuid.toString())) + " seconds.");
						} else {
							sender.sendMessage(ChatColor.GREEN + "He will never be unbanned unless you do /unsban.");
						}
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
		
		return false;
	}
}