package org.glockinmybape.tattyguardian;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.glockinmybape.tattyguardian.commands.AGCommand;
import org.glockinmybape.tattyguardian.listeners.AntiTabCompleter;
import org.glockinmybape.tattyguardian.listeners.BedrockFixer;
import org.glockinmybape.tattyguardian.listeners.ChunkLimiter;
import org.glockinmybape.tattyguardian.listeners.Pistons;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TattyGuardian extends JavaPlugin {
    public static TattyGuardian instance;
    public static FileConfiguration cfg;
    public static List<String> commandArgs = Arrays.asList("reload");

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        cfg = this.getConfig();
        this.printLogo();
        this.registerEvents();
        this.registerCmd();
    }

    public void onDisable() {
        this.printLogo();
    }

    private void registerCmd() {
        this.getCommand("tattyguard").setExecutor(new AGCommand());
        this.getCommand("tattyguard").setTabCompleter(new TabCompleter() {
            public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
                return args.length == 1 ? TattyGuardian.commandArgs : null;
            }
        });
    }

    private void registerEvents() {
        List<Listener> listeners = Arrays.asList(new AntiTabCompleter(), new BedrockFixer(), new ChunkLimiter(), new Pistons());
        PluginManager pm = Bukkit.getPluginManager();
        listeners.forEach((listener) -> {
            pm.registerEvents(listener, this);
        });
    }

    private void printLogo() {
        Logger log = Bukkit.getLogger();
        log.info("§b");
        log.info("§b .----------------------------------------------------------. ");
        log.info("§b| .-------------------------------------------------------. |");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| |            §7Плагин: §bTattyGuardian§8| §7Версия: §b1.0                ");
        log.info("§b| |        §7Создан для §bTattyWorld §8- §7Разработал: §bglockinmybape\t");
        log.info("§b| |                    §bvk.com/TattyWorld");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| '-------------------------------------------------------'§b|");
        log.info("§b'-----------------------------------------------------------'");
        log.info("§b");
    }
}
