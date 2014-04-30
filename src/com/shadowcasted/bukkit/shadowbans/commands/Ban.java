package com.shadowcasted.bukkit.shadowbans.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.shadowcasted.bukkit.shadowbans.ShadowBans;
import com.shadowcasted.bukkit.shadowbans.config.sconfig;

public class Ban
  implements CommandExecutor
{
  final ShadowBans plugin;

  public Ban(ShadowBans instance)
  {
    this.plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender.isOp()) || (sender.hasPermission("ShadowBans.ban")))
    {
      if (args.length == 0)
      {
        sender.sendMessage(this.plugin.prefix + "Usage: /" + label + " <player> <reason>");
      }
      else if (args.length == 1)
      {
        Player banned = this.plugin.getServer().getPlayer(args[0]);
        OfflinePlayer offlinebanned = this.plugin.getServer().getOfflinePlayer(args[0]);

        if (banned != null)
        {
          String reason = "Banned!";

          banned.kickPlayer("You were banned from the server.\nReason: §c" + reason);
          banned.setBanned(true);

          this.plugin.getServer().broadcastMessage("§7" + banned.getName() + " was banned from the server by " + sender.getName());

          this.plugin.sconfig.getConfig().set(banned.getName().toLowerCase(), reason);
          this.plugin.sconfig.saveConfig();
        }
        else
        {
          String reason = "Banned!";

          offlinebanned.setBanned(true);

          this.plugin.getServer().broadcastMessage("§7" + offlinebanned.getName() + " was banned from the server by " + sender.getName());

          this.plugin.sconfig.getConfig().set(offlinebanned.getName().toLowerCase(), reason);
          this.plugin.sconfig.saveConfig();
        }
      }
      else if (args.length >= 2)
      {
        Player banned = this.plugin.getServer().getPlayer(args[0]);
        OfflinePlayer offlinebanned = this.plugin.getServer().getOfflinePlayer(args[0]);

        if (banned != null)
        {
          String reason = "";

          for (int i = 1; i < args.length; i++)
          {
            reason = reason + args[i] + " ";
          }

          banned.kickPlayer("You were banned from the server.\nReason: §c" + reason);
          banned.setBanned(true);

          this.plugin.getServer().broadcastMessage("§7" + banned.getName() + " was banned from the server by " + sender.getName() + " for " + reason);

          this.plugin.sconfig.getConfig().set(banned.getName().toLowerCase(), reason);
          this.plugin.sconfig.saveConfig();
        }
        else
        {
          String reason = "";

          for (int i = 1; i < args.length; i++)
          {
            reason = reason + args[i] + " ";
          }

          offlinebanned.setBanned(true);

          this.plugin.getServer().broadcastMessage("§7" + offlinebanned.getName() + " was banned from the server by " + sender.getName() + " for " + reason);

          this.plugin.sconfig.getConfig().set(offlinebanned.getName().toLowerCase(), reason);
          this.plugin.sconfig.saveConfig();
        }
      }
    }

    return false;
  }
}