package com.shadowcasted.bukkit.shadowbans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import com.shadowcasted.bukkit.shadowbans.ShadowBans;
import com.shadowcasted.bukkit.shadowbans.config.sconfig;

public class CheckBan
  implements CommandExecutor
{
  final ShadowBans plugin;

  public CheckBan(ShadowBans instance)
  {
    this.plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("ShadowBans.check")))
    {
      if (args.length == 0)
      {
        sender.sendMessage(this.plugin.prefix + "Usage: /" + label + " <player>");
      }
      else if (args.length == 1)
      {
        if (this.plugin.sconfig.getConfig().contains(args[0].toLowerCase()))
        {
          sender.sendMessage(this.plugin.prefix + args[0] + " is banned from the server for: §c" + this.plugin.sconfig.getConfig().getString(args[0].toLowerCase()));
        }
        else
        {
          sender.sendMessage(this.plugin.prefix + "That player (§e" + args[0] + "§r) is not banned.");
        }
      }
    }

    return false;
  }
}