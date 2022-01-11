package dev.floffah.plugin.piratemechanics.namespace;

import dev.floffah.plugin.piratemechanics.PirateMechanics;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class NamespacedKeyMap {

    Map<String, NamespacedKey> keys;
    PirateMechanics plugin;

    public NamespacedKeyMap(PirateMechanics pl) {
        keys = new HashMap<String, NamespacedKey>();
        plugin = pl;
    }

    public NamespacedKey get(String name) {
        if (keys.containsKey(name)) {
            return keys.get(name);
        } else {
            NamespacedKey key = new NamespacedKey(plugin, name);
            keys.put(name, key);
            return key;
        }
    }
}