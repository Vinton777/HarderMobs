package me.vinton777.hardermobs.power;

import me.vinton777.hardermobs.HarderMobs;
import me.vinton777.hardermobs.mobs.MobStats;
import me.vinton777.hardermobs.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

public class PowerManager {

    private final HarderMobs plugin;
    private boolean powered = false;

    public PowerManager(HarderMobs plugin) {
        this.plugin = plugin;
    }

    public void enable(boolean manual) {
        if (powered) return;
        powered = true;

        applyToAllMobs();

        MessageUtil.broadcast(plugin.getConfigManager()
                .msg(manual ? "manual-enabled" : "enabled"));
    }

    public void disable(boolean manual) {
        if (!powered) return;
        powered = false;

        MessageUtil.broadcast(plugin.getConfigManager()
                .msg(manual ? "manual-disabled" : "disabled"));
    }

    public boolean isPowered() {
        return powered;
    }

    private void applyToAllMobs() {
        MobStats stats = plugin.getConfigManager().getDefaultStats();

        Bukkit.getWorlds().forEach(world ->
                world.getEntitiesByClass(Monster.class).forEach(monster -> {

                    var attr = monster.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    if (attr == null) return;

                    double base = attr.getBaseValue();
                    attr.setBaseValue(base * stats.getHealthMultiplier());
                    monster.setHealth(attr.getBaseValue());
                })
        );
    }
}
