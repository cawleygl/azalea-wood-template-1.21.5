package bluesteel42.azaleawood.item;

import bluesteel42.azaleawood.AzaleaWood;
import bluesteel42.azaleawood.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item AZALEA_SIGN = registerSignItem("azalea_sign");
    public static final Item AZALEA_HANGING_SIGN = registerHangingSignItem("azalea_hanging_sign");

    public static Item registerSignItem(String path) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AzaleaWood.MOD_ID, path));
        final Item item = new SignItem(
                ModBlocks.AZALEA_STANDING_SIGN,
                ModBlocks.AZALEA_WALL_SIGN,
                new Item.Settings().registryKey(registryKey).maxCount(16));

        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static Item registerHangingSignItem(String path) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AzaleaWood.MOD_ID, path));
        final Item item = new HangingSignItem(
                ModBlocks.AZALEA_HANGING_SIGN,
                ModBlocks.AZALEA_WALL_HANGING_SIGN,
                new Item.Settings().registryKey(registryKey).maxCount(16));

        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.BAMBOO_HANGING_SIGN, ModItems.AZALEA_HANGING_SIGN);
                    itemGroup.addAfter(Items.BAMBOO_HANGING_SIGN, ModItems.AZALEA_SIGN);
                });

    }
}
