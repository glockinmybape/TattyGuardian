package org.glockinmybape.tattyguardian.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
