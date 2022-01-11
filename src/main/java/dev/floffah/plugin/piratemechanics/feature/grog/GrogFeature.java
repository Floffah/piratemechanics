package dev.floffah.plugin.piratemechanics.feature.grog;

import dev.floffah.plugin.piratemechanics.feature.BaseFeature;
import dev.floffah.plugin.piratemechanics.feature.Feature;

@Feature(name = "Grog")
public class GrogFeature extends BaseFeature {

    public GrogFeature() {
        super(plugin -> plugin.config.config.mechanics.grogEnabled);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
