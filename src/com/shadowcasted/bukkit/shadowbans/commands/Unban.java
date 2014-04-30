package com.shadowcasted.bukkit.shadowbans.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.shadowcasted.bukkit.shadowbans.ShadowBans;
import com.shadowcasted.bukkit.shadowbans.config.sconfig;

public class Unban
  implements CommandExecutor
{
  final ShadowBans plugin;

  public Unban(ShadowBans instance)
  {
    this.plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("ShadowBans.unban")))
    {
      if (args.length == 0)
      {
        sender.sendMessage(this.plugin.prefix + "§7" + "Usage: /" + label + " <player>");
      }
      else if (args.length >= 1)
      {
        OfflinePlayer banned = this.plugin.getServer().getOfflinePlayer(args[0]);

        if (banned != null)
        {
          if (banned.isBanned())
          {
            this.plugin.sconfig.getConfig().set(banned.getName().toLowerCase(), null);
            this.plugin.sconfig.saveConfig();

            banned.setBanned(false);

            this.plugin.getServer().broadcastMessage("§7" + banned.getName().toLowerCase() + " has been unbanned");
          }
          else
          {
            sender.sendMessage(this.plugin.prefix + "§7" + "That player isn't banned!");
          }
        }
        else
        {
          sender.sendMessage(this.plugin.prefix + "§7" + "That player has never been online before!");
        }
      }
    }

    return true;
  }
}