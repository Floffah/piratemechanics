package dev.floffah.plugin.piratemechanics.feature;

import dev.floffah.plugin.piratemechanics.PirateMechanics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Predicate;

@Retention(RetentionPolicy.RUNTIME)
public @interface Feature {
    String name();
}
