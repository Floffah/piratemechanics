package dev.floffah.plugin.piratemechanics.feature.grog;

import dev.floffah.plugin.piratemechanics.feature.BaseFeature;
import dev.floffah.plugin.piratemechanics.feature.Feature;
import dev.floffah.plugin.piratemechanics.namespace.NamespacedKeys;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

@Feature(name = "Grog")
public class GrogFeature extends BaseFeature implements Listener {

    public GrogFeature() {
        super(plugin -> plugin.config.config.mechanics.grogEnabled);
    }

    private ShapedRecipe getEmptyGrogRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.features.plugin.nkMap.get(
                    NamespacedKeys.EMPTY_GROG_CUP_RECIPE
                ),
            GrogItems.emptyGrogCup()
        );

        recipe.shape("O O", "O O", "OOO");
        recipe.setIngredient('O', Material.OAK_PLANKS);

        return recipe;
    }

    private ShapedRecipe getGrogBarrelRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(
            this.features.plugin.nkMap.get(NamespacedKeys.GROG_BARREL_RECIPE),
            GrogItems.grogBarrel()
        );

        recipe.shape("OOO", "OBO", "OOO");
        recipe.setIngredient('O', Material.OAK_PLANKS);
        recipe.setIngredient('B', Material.WATER_BUCKET);

        return recipe;
    }

    @Override
    public void onEnable() {
        this.features.plugin.getServer().addRecipe(this.getEmptyGrogRecipe());
        this.features.plugin.getServer().addRecipe(this.getGrogBarrelRecipe());

        this.features.plugin.getServer()
            .getPluginManager()
            .registerEvents(this, this.features.plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = e.getItem();
            if (item != null && item.getType().equals(Material.BARREL)) {
                ItemMeta meta = item.getItemMeta();
                if (
                    meta.hasCustomModelData() &&
                    meta.getCustomModelData() == 5000
                ) {
                    Block block = e.getClickedBlock();
                    if (block == null) return; else e.setCancelled(true);
                    if (!e.getBlockFace().equals(BlockFace.UP)) block =
                        block.getRelative(e.getBlockFace());
                    if (e.getBlockFace().equals(BlockFace.DOWN)) block =
                        block.getRelative(e.getBlockFace()); // do it again to make the barrel visible as it is on the head of the armor stand or on the floor
                    else if (!e.getBlockFace().equals(BlockFace.UP)) block =
                        block.getRelative(BlockFace.DOWN);

                    Location armorL = block.getLocation().add(0.5, 0, 0.5);
                    Vector playerV = p.getLocation().toVector();
                    Vector armorV = armorL.toVector();
                    Vector facingV = playerV.subtract(armorV).normalize();
                    armorL.setDirection(facingV);

                    ArmorStand armorStand = p
                        .getWorld()
                        .spawn(armorL, ArmorStand.class);
                    armorStand.setInvisible(true);
                    armorStand.setGravity(false);
                    armorStand.setInvulnerable(true);
                    armorStand.getEquipment().setHelmet(GrogItems.grogBarrel());

                    if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                        if (item.getAmount() > 1) {
                            item.setAmount(item.getAmount() - 1);
                            p
                                .getInventory()
                                .setItem(
                                    p.getInventory().getHeldItemSlot(),
                                    item
                                );
                        } else p.getInventory().removeItem(item);
                    }

                    p
                        .getWorld()
                        .playSound(
                            block.getLocation(),
                            Sound.BLOCK_WOOD_PLACE,
                            10,
                            1
                        );
                    p
                        .getWorld()
                        .playSound(
                            block.getLocation(),
                            Sound.ITEM_BUCKET_FILL,
                            10,
                            1
                        );
                }
            }
        }
    }

    @Override
    public void onDisable() {
        this.features.plugin.getServer()
            .removeRecipe(
                this.features.plugin.nkMap.get(
                        NamespacedKeys.EMPTY_GROG_CUP_RECIPE
                    )
            );
        this.features.plugin.getServer()
            .removeRecipe(
                this.features.plugin.nkMap.get(
                        NamespacedKeys.GROG_BARREL_RECIPE
                    )
            );

        PlayerInteractEvent.getHandlerList().unregister(this);
    }
}
