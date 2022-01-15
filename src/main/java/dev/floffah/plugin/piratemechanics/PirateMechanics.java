package dev.floffah.plugin.piratemechanics;

import dev.floffah.plugin.piratemechanics.commands.PirateMechanicsCommand;
import dev.floffah.plugin.piratemechanics.config.ConfigManager;
import dev.floffah.plugin.piratemechanics.feature.FeatureManager;
import dev.floffah.plugin.piratemechanics.namespace.NamespacedKeyMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class PirateMechanics extends JavaPlugin {
    public ConfigManager config;
    public FeatureManager features;
    public NamespacedKeyMap nkMap;

    @Override
    public void onEnable() {
        try {
            this.config = new ConfigManager(this);
            this.config.initConfig();
        } catch (IOException e) {
            this.getLog4JLogger().fatal("Could not initialise PirateMechanics config", e);
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.nkMap = new NamespacedKeyMap(this);

        this.features = new FeatureManager(this);
        try {
            this.features.loadFeatures();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            this.getLog4JLogger().fatal("Could not load features", e);
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PirateMechanicsCommand pm = new PirateMechanicsCommand(this);
        PluginCommand pmcommand = this.getCommand("piratemechanics");
        pmcommand.setExecutor(pm);
        pmcommand.setTabCompleter(pm);

        this.getLog4JLogger().info("Enabled PirateMechanics");
    }

    @Override
    public void onDisable() {
        this.features.unloadFeatures();

        this.getLog4JLogger().info("Disabled PirateMechanics");
    }
}
