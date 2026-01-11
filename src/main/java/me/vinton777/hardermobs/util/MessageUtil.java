package me.vinton777.hardermobs.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtil {

    public static void broadcast(String msg) {
        if (msg == null) return;
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
