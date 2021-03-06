package dev.floffah.plugin.piratemechanics.feature;

import dev.floffah.plugin.piratemechanics.PirateMechanics;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;

public class FeatureManager {

    public final PirateMechanics plugin;

    public List<BaseFeature> features = new ArrayList<>();

    public FeatureManager(PirateMechanics plugin) {
        this.plugin = plugin;
    }

    public void loadFeatures()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections(
            "dev.floffah.plugin.piratemechanics.feature"
        );
        Set<Class<?>> features = reflections.getTypesAnnotatedWith(
            Feature.class
        );

        for (Class<?> feature : features) {
            if (BaseFeature.class.isAssignableFrom(feature)) {
                Class<? extends BaseFeature> validFeature = (Class<? extends BaseFeature>) feature;
                Feature featureAnnotation = validFeature.getAnnotation(
                    Feature.class
                );

                BaseFeature constructedFeature = validFeature
                    .getConstructor()
                    .newInstance();
                constructedFeature.features = this;
                constructedFeature.onLoad();
                constructedFeature.name = featureAnnotation.name();

                boolean shouldEnable = constructedFeature.shouldEnable.test(
                    this.plugin
                );
                if (shouldEnable) {
                    constructedFeature.enabled = true;
                    constructedFeature.onEnable();
                }
                this.features.add(constructedFeature);
                this.plugin.getLog4JLogger()
                    .info("Loaded feature " + constructedFeature.name);
            }
        }
    }

    public void unloadFeatures() {
        // copies out array to prevent ConcurrentModificationExceptions
        List<BaseFeature> toRemove = new ArrayList<>();
        toRemove.addAll(this.features);

        for (BaseFeature feature : toRemove) {
            if (feature.enabled) feature.onDisable();
            feature.onUnload();
            this.features.remove(feature);
        }

        toRemove.clear(); // idk im paranoid
    }

    public void reloadFeatures() {
        for (BaseFeature feature : this.features) {
            boolean shouldEnable = feature.shouldEnable.test(this.plugin);
            if (!feature.enabled && shouldEnable) {
                feature.enabled = true;
                feature.onEnable();
            } else if (feature.enabled && !shouldEnable) {
                feature.enabled = false;
                feature.onDisable();
            }
        }
    }
}
