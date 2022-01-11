package dev.floffah.plugin.piratemechanics.commands;

import dev.floffah.plugin.piratemechanics.PirateMechanics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PirateMechanicsCommand implements CommandExecutor, TabCompleter {

    PirateMechanics plugin;

    public PirateMechanicsCommand(PirateMechanics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
        @NotNull CommandSender sender,
        @NotNull Command command,
        @NotNull String label,
        @NotNull String[] args
    ) {
        if (args.length >= 1) {
            if (args[0].equals("reload")) {
                try {
                    this.plugin.config.readConfig();
                    this.plugin.features.reloadFeatures();
                } catch (IOException e) {
                    sender.sendMessage(
                        Component
                            .text("An error occurred, check console")
                            .color(NamedTextColor.RED)
                    );
                    e.printStackTrace();
                }
            } else if (args[0].equals("hardreload")) {
                try {
                    this.plugin.config.readConfig();
                    this.plugin.features.unloadFeatures();
                    this.plugin.features.loadFeatures();
                } catch (
                    IOException
                    | InvocationTargetException
                    | IllegalAccessException
                    | InstantiationException
                    | NoSuchMethodException e
                ) {
                    sender.sendMessage(
                        Component
                            .text("An error occurred, check console")
                            .color(NamedTextColor.RED)
                    );
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(
        @NotNull CommandSender sender,
        @NotNull Command command,
        @NotNull String alias,
        @NotNull String[] args
    ) {
        if (args.length == 1) {
            return List.of("reload", "hardreload");
        }

        return null;
    }
}
