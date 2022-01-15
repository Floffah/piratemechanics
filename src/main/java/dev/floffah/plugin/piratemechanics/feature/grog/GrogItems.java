package dev.floffah.plugin.piratemechanics.feature.grog;

import dev.floffah.plugin.piratemechanics.util.Colors.DefaultColors;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class GrogItems {

    public static ItemStack emptyGrogCup() {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.displayName(
            Component
                .text("Empty Grog Cup")
                .color(DefaultColors.grogItemColor().textColor())
        );
        meta.lore(
            List.of(
                Component
                    .text("Fill it up at a grog barrel!")
                    .color(DefaultColors.loreColor().textColor())
            )
        );

        meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE));
        //        meta.addCustomEffect(
        //            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 0, 255),
        //            true
        //        );
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);

        meta.setCustomModelData(5000);

        potion.setItemMeta(meta);
        return potion;
    }

    public static ItemStack fullGrogCup() {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.displayName(
            Component
                .text("Full Grog Cup")
                .color(DefaultColors.grogItemColor().textColor())
        );
        meta.lore(
            List.of(
                Component
                    .text("You are a true pirate. CHEERS!")
                    .color(DefaultColors.loreColor().textColor())
            )
        );

        meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE));
        //        meta.addCustomEffect(
        //            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 0, 255),
        //            true
        //        );
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(5001);

        potion.setItemMeta(meta);
        return potion;
    }

    public static ItemStack grogBarrel() {
        ItemStack barrel = new ItemStack(Material.BARREL);
        ItemMeta meta = barrel.getItemMeta();

        meta.displayName(
            Component
                .text("Grog Barrel")
                .color(DefaultColors.grogItemColor().textColor())
        );
        meta.lore(
            List.of(
                Component
                    .text("Place this on a surface to get INFINITE GROG")
                    .color(DefaultColors.loreColor().textColor())
            )
        );
        meta.setCustomModelData(5000);

        barrel.setItemMeta(meta);
        return barrel;
    }
}
