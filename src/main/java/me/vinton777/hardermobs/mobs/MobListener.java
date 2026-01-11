package me.vinton777.hardermobs.mobs;

import me.vinton777.hardermobs.HarderMobs;
import me.vinton777.hardermobs.power.PowerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobListener implements Listener {

    private final HarderMobs plugin;
    private final PowerManager powerManager;

    public MobListener(HarderMobs plugin) {
        this.plugin = plugin;
        this.powerManager = plugin.getPowerManager();
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Monster monster)) return;
        if (!powerManager.isPowered()) return;

        MobStats stats = plugin.getConfigManager().getDefaultStats();

        LivingEntity entity = monster;
        double baseHp = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .setBaseValue(baseHp * stats.getHealthMultiplier());
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Monster)) return;

        MobStats stats = plugin.getConfigManager().getDefaultStats();

        event.setDamage(
                powerManager.isPowered()
                        ? stats.getPoweredDamage()
                        : stats.getNormalDamage()
        );
    }
}
