package com.shadowcasted.shadowbans.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shadowcasted.shadowbans.ShadowBans;

public class Uuid implements CommandExecutor {
	final ShadowBans plugin;

	public Uuid(ShadowBans instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("ShadowBans.uuid")) {
			if (args.length == 1) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getName().equals(args[0])) {
						sender.sendMessage(ChatColor.GREEN + "The UUID of the player " + args[0] + " is :");
						sender.sendMessage(ChatColor.GREEN + player.getUniqueId().toString());
						break;
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Error with arguments : /" + label + " <player>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have the permissions!");
		}

		return true;
	}
}