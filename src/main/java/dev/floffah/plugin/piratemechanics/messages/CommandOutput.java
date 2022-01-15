package dev.floffah.plugin.piratemechanics.messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class CommandOutput {

    public static Component defaultFormat(Component text) {
        return Component
            .empty()
            .append(
                Component
                    .text("Pirate")
                    .color(NamedTextColor.GOLD)
                    .decorate(TextDecoration.BOLD)
            )
            .append(
                Component
                    .text("Mechanics")
                    .color(NamedTextColor.RED)
                    .decorate(TextDecoration.BOLD)
            )
            .append(Component.text(" » ").color(NamedTextColor.GRAY))
            .append(text);
    }
}
