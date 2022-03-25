package org.glockinmybape.tattyguardian.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.glockinmybape.tattyguardian.TattyGuardian;
import org.bukkit.configuration.InvalidConfigurationException;

public class config {
    public static boolean loadCfg() {
        File fileCfg = new File(TattyGuardian.instance.getDataFolder(), "config.yml");

        try {
            TattyGuardian.cfg.load(fileCfg);
            return true;
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static List<String> getBLCBlocks() {
        return TattyGuardian.cfg.getStringList("BlockLimiterChunk.blocks");
    }

    public static String getBLCMessage() {
        return TattyGuardian.cfg.getString("BlockLimiterChunk.message");
    }

    public static boolean isBedrockFixEnabled() {
        return TattyGuardian.cfg.getBoolean("Fixers.BedrockFix");
    }

    public static boolean isTabEnabled() {
        return TattyGuardian.cfg.getBoolean("Fixers.TabComplete");
    }

    public static boolean enabledOtherBlocks() {
        return !getOtherBlocks().isEmpty();
    }

    public static List<String> getOtherBlocks() {
        return TattyGuardian.cfg.getStringList("CrashPistons.fix.other_blocks");
    }
}
