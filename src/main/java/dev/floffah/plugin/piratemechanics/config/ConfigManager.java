package dev.floffah.plugin.piratemechanics.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dev.floffah.plugin.piratemechanics.PirateMechanics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {
    private final PirateMechanics plugin;
    public Config config;
    private File configFile;

    private ObjectMapper om;

    public ConfigManager(PirateMechanics plugin) {
        this.plugin = plugin;
    }

    public void initConfig() throws IOException {
        this.configFile = new File(Path.of(this.plugin.getDataFolder().getPath(), "config.yml").toUri());

        YAMLFactory yf = new YAMLFactory();
//        yf.disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID);

        this.om = new ObjectMapper(yf);

        if (!this.plugin.getDataFolder().exists()) this.plugin.getDataFolder().mkdirs();
        if (!this.configFile.exists()) {
            this.config = new Config();
            this.writeConfig();
        } else {
            this.readConfig();
        }
    }

    public void readConfig() throws IOException {
        this.config = this.om.readValue(this.configFile, Config.class);
    }

    public void writeConfig() throws IOException {
        this.om.writeValue(this.configFile, this.config);
    }
}
