package me.vinton777.hardermobs;

import me.vinton777.hardermobs.command.HarderMobsCommand;
import me.vinton777.hardermobs.config.ConfigManager;
import me.vinton777.hardermobs.mobs.MobListener;
import me.vinton777.hardermobs.power.PowerManager;
import me.vinton777.hardermobs.tasks.AutoPowerTask;
import org.bukkit.plugin.java.JavaPlugin;

public class HarderMobs extends JavaPlugin {

    private static HarderMobs instance;

    private ConfigManager configManager;
    private PowerManager powerManager;

    @Override
    public void onEnable() {
        instance = this;

        // config
        saveDefaultConfig();
        configManager = new ConfigManager(this);

        // power system
        powerManager = new PowerManager(this);

        // commands
        if (getCommand("hardermobs") != null) {
            getCommand("hardermobs").setExecutor(new HarderMobsCommand(this));
        }

        // listeners
        getServer().getPluginManager().registerEvents(
                new MobListener(this), this
        );

        // auto power by days
        AutoPowerTask.start(this);

        getLogger().info("HarderMobs enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("HarderMobs disabled");
    }

    public static HarderMobs getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }
}
