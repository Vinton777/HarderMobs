package me.vinton777.hardermobs.power;

import me.vinton777.hardermobs.HarderMobs;
import me.vinton777.hardermobs.util.MessageUtil;

public class PowerManager {

    private final HarderMobs plugin;
    private boolean powered = false;

    public PowerManager(HarderMobs plugin) {
        this.plugin = plugin;
    }

    public void enable(boolean manual) {
        if (powered) return;
        powered = true;
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
}
