package com.shadowcasted.shadowbans.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shadowcasted.shadowbans.ShadowBans;

public class Ban implements CommandExecutor {
	final ShadowBans plugin;

	public Ban(ShadowBans instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("ShadowBans.ban")) {
			if (args.length >= 1) {
				UUID uuid = null;
				if (args[0].matches("[0-9a-zA-Z-]{36}")) {
					if (Bukkit.getPlayer(UUID.fromString(args[0])).isValid()) {
						uuid = UUID.fromString(args[0]);
					}
				}
				if (uuid == null) {
					for (Player test : Bukkit.getOnlinePlayers()) {
						if (test.getName().equals(args[0])) {
							uuid = test.getUniqueId();
							break;
						}
					}
				}
				if (uuid != null) {
					String reason = "";
					args[0] = null;
					for (String word : args) {
						if (word != null) {
							reason += word + " ";
						}
					}
					reason = reason.substring(0, reason.length()-1);
					if (args.length == 1) {
						reason = ChatColor.DARK_RED + "You have been banned by " + ChatColor.RED + sender.getName() + ".";
					}

					ShadowBans.sconfig.getConfig().set("bans." + uuid.toString(), reason);
					ShadowBans.sconfig.saveConfig();

					if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
						Bukkit.getPlayer(uuid).kickPlayer(reason);
					}
				} else {
					sender.sendMessage(ChatColor.RED + "First argument must be an online player name or a UUID.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Error with arguments : /" + label + " <player/UUID> [reason]");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have the permissions!");
		}

		return false;
	}
}