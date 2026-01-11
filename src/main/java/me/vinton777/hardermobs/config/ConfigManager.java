package me.vinton777.hardermobs.config;

import me.vinton777.hardermobs.mobs.MobStats;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    private final JavaPlugin plugin;
    private MobStats defaultStats;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        double normal = plugin.getConfig().getDouble("stats.default.normal-damage");
        double powered = plugin.getConfig().getDouble("stats.default.powered-damage");
        double health = plugin.getConfig().getDouble("stats.default.health-multiplier");

        defaultStats = new MobStats(normal, powered, health);
    }

    public MobStats getDefaultStats() {
        return defaultStats;
    }

    public int enableAfterDays() {
        return plugin.getConfig().getInt("days.enable-after");
    }

    public int durationDays() {
        return plugin.getConfig().getInt("days.duration");
    }

    public String msg(String path) {
        return plugin.getConfig().getString("messages." + path);
    }

    public void reload() {
        plugin.reloadConfig();
        load();
    }
}
