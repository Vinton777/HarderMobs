package me.vinton777.hardermobs.mobs;

public class MobStats {

    private final double normalDamage;
    private final double poweredDamage;
    private final double healthMultiplier;

    public MobStats(double normalDamage, double poweredDamage, double healthMultiplier) {
        this.normalDamage = normalDamage;
        this.poweredDamage = poweredDamage;
        this.healthMultiplier = healthMultiplier;
    }

    public double getNormalDamage() {
        return normalDamage;
    }

    public double getPoweredDamage() {
        return poweredDamage;
    }

    public double getHealthMultiplier() {
        return healthMultiplier;
    }
}
