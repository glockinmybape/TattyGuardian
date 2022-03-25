package org.glockinmybape.tattyguardian.commands;

import org.glockinmybape.tattyguardian.TattyGuardian;
import org.glockinmybape.tattyguardian.utils.config;
import org.glockinmybape.tattyguardian.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AGCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (!(sender instanceof Player)) {
            this.sendMsg(sender, TattyGuardian.cfg.getString("Command.only_players"));
            return true;
        } else if (!sender.hasPermission("tattyguard.admin")) {
            this.sendMsg(sender, TattyGuardian.cfg.getString("Command.perms"));
            return true;
        } else if (args.length == 0) {
            TattyGuardian.cfg.getStringList("Command.help").forEach((msg) -> {
                this.sendMsg(sender, msg);
            });
            return true;
        } else {
            if (args.length >= 1) {
                if (!TattyGuardian.commandArgs.contains(args[0])) {
                    this.sendMsg(sender, TattyGuardian.cfg.getString("Command.cmd_not_found"));
                    return true;
                }

                if (args[0].equalsIgnoreCase("reload")) {
                    if (config.loadCfg()) {
                        this.sendMsg(sender, TattyGuardian.cfg.getString("Command.reload.successful"));
                        return true;
                    }

                    this.sendMsg(sender, TattyGuardian.cfg.getString("Command.reload.bad"));
                    return true;
                }
            }

            return true;
        }
    }

    private void sendMsg(CommandSender sender, String text) {
        sender.sendMessage(Utils.colorize(text));
    }
}
