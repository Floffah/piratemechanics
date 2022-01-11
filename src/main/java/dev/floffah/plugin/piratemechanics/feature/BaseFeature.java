package dev.floffah.plugin.piratemechanics.feature;

import dev.floffah.plugin.piratemechanics.PirateMechanics;
import java.util.function.Predicate;

public class BaseFeature {

    public Predicate<PirateMechanics> shouldEnable;
    public FeatureManager features;
    public boolean enabled;

    public BaseFeature(Predicate<PirateMechanics> shouldEnable) {
        this.shouldEnable = shouldEnable;
    }

    public void onLoad() {}

    public void onEnable() {}

    public void onDisable() {}

    public void onUnload() {}
}
