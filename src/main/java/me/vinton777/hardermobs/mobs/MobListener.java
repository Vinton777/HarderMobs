package me.vinton777.hardermobs.mobs;

import me.vinton777.hardermobs.HarderMobs;
import me.vinton777.hardermobs.power.PowerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class MobListener implements Listener {

    private final HarderMobs plugin;
    private final PowerManager powerManager;

    public MobListener(HarderMobs plugin) {
        this.plugin = plugin;
        this.powerManager = plugin.getPowerManager();
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (!powerManager.isPowered()) return;
        if (!(event.getEntity() instanceof Monster monster)) return;

        MobStats stats = plugin.getConfigManager().getDefaultStats();

        var attr = monster.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attr == null) return;

        double base = attr.getBaseValue();
        attr.setBaseValue(base * stats.getHealthMultiplier());
        monster.setHealth(attr.getBaseValue());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!powerManager.isPowered()) return;

        LivingEntity attacker = null;

        // Прямой урон
        if (event.getDamager() instanceof Monster monster) {
            attacker = monster;
        }

        // Урон от стрел / фаерболов и т.д.
        if (event.getDamager() instanceof Projectile projectile) {
            ProjectileSource source = projectile.getShooter();
            if (source instanceof Monster monster) {
                attacker = monster;
            }
        }

        if (attacker == null) return;

        MobStats stats = plugin.getConfigManager().getDefaultStats();
        event.setDamage(event.getDamage() * stats.getPoweredDamage());
    }
}
