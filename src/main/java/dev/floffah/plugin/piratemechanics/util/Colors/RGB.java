package dev.floffah.plugin.piratemechanics.util.Colors;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.Range;

public class RGB implements RGBLike {

    @Range(from = 0L, to = 255L)
    public int r;

    @Range(from = 0L, to = 255L)
    public int b;

    @Range(from = 0L, to = 255L)
    public int g;

    public RGB(
        @Range(from = 0L, to = 255L) int r,
        @Range(from = 0L, to = 255L) int b,
        @Range(from = 0L, to = 255L) int g
    ) {
        this.r = r;
        this.b = b;
        this.g = g;
    }

    @Override
    public @Range(from = 0L, to = 255L) int red() {
        return r;
    }

    @Override
    public @Range(from = 0L, to = 255L) int green() {
        return g;
    }

    @Override
    public @Range(from = 0L, to = 255L) int blue() {
        return b;
    }

    public TextColor textColor() {
        return TextColor.color(this);
    }
}
