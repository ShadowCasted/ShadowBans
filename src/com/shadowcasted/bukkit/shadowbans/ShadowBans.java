package com.shadowcasted.bukkit.shadowbans;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.shadowcasted.bukkit.shadowbans.commands.Ban;
import com.shadowcasted.bukkit.shadowbans.commands.CheckBan;
import com.shadowcasted.bukkit.shadowbans.commands.Unban;
import com.shadowcasted.bukkit.shadowbans.config.sconfig;

public class ShadowBans extends JavaPlugin
{
  public static ShadowBans plugin;
  public String prefix = "§eEB §7> §r ";
  public sconfig sconfig;

  public ShadowBans()
  {
    this.sconfig = new sconfig(this);
  }

  public void onEnable()
  {
    setup();
    getServer().getLogger().info("ShadowBans is enabled.");
  }

  public void onDisable()
  {
    getServer().getLogger().info("ShadowBans is disabled.");
  }

  void setup()
  {
    getCommand("eban").setExecutor(new Ban(this));
    getCommand("eunban").setExecutor(new Unban(this));
    getCommand("CheckBan").setExecutor(new CheckBan(this));

  }

  @EventHandler(priority=EventPriority.MONITOR)
  public void onJoin(AsyncPlayerPreLoginEvent event)
  {
    String player = event.getName().toLowerCase();

    if (this.plugin.sconfig.getConfig().getString(player) != null)
    {
      event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "You are banned from this server.\nReason: §c" + this.plugin.sconfig.getConfig().getString(player));
    }
  }
}