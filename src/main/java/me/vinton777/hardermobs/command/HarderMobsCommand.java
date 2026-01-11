package me.vinton777.hardermobs.command;

import me.vinton777.hardermobs.HarderMobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HarderMobsCommand implements CommandExecutor {

    private final HarderMobs plugin;

    public HarderMobsCommand(HarderMobs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("hardermobs.admin")) {
            sender.sendMessage("§cНет прав");
            return true;
        }

        if (args.length == 0) return false;

        switch (args[0].toLowerCase()) {
            case "on" -> plugin.getPowerManager().enable(true);
            case "off" -> plugin.getPowerManager().disable(true);
            case "reload" -> {
                plugin.getConfigManager().reload();
                sender.sendMessage("§aConfig перезагружен");
            }
        }
        return true;
    }
}
