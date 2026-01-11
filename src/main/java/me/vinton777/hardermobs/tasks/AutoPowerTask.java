package me.vinton777.hardermobs.tasks;

import me.vinton777.hardermobs.HarderMobs;
import org.bukkit.Bukkit;

public class AutoPowerTask {

    public static void start(HarderMobs plugin) {
        long day = 24000L;
        long start = plugin.getConfigManager().enableAfterDays() * day;
        long duration = plugin.getConfigManager().durationDays() * day;

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            plugin.getPowerManager().enable(false);

            Bukkit.getScheduler().runTaskLater(plugin, () ->
                    plugin.getPowerManager().disable(false), duration);

        }, start, start + duration);
    }
}
