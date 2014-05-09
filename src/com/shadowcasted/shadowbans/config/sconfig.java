package com.shadowcasted.shadowbans.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.shadowcasted.shadowbans.ShadowBans;

public class sconfig {
	final ShadowBans plugin;
	public FileConfiguration config = null;
	public File configFile = null;

	public sconfig(ShadowBans instance) {
		this.plugin = instance;
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public FileConfiguration getConfig() {
		if (this.config == null) {
			reloadConfig();
		}

		return this.config;
	}

	public void reloadConfig() {
		if (this.configFile == null) {
			this.configFile = new File(this.plugin.getDataFolder(), "banned.yml");
		}

		this.config = YamlConfiguration.loadConfiguration(this.configFile);
	}

	public void saveConfig() {
		if ((this.config == null) || (this.configFile == null)) {
			return;
		}

		try {
			this.config.save(this.configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}