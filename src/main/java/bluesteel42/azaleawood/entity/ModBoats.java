package bluesteel42.azaleawood.entity;

import bluesteel42.azaleawood.AzaleaWood;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBoats {

    public static final Identifier AZALEA_RAFTS_ID = Identifier.of(AzaleaWood.MOD_ID, "azalea");

    public static final Item AZALEA_RAFT = registerBoatItem("azalea_raft", ModBoats.AZALEA_RAFTS_ID, false, true);
    public static final Item AZALEA_CHEST_RAFT = registerBoatItem("azalea_chest_raft", ModBoats.AZALEA_RAFTS_ID, true, true);

    public static Item registerBoatItem(String path, Identifier boatId, boolean chest, boolean raft) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AzaleaWood.MOD_ID, path));
        return TerraformBoatItemHelper.registerBoatItem(
                boatId,
                new Item.Settings().maxCount(1).registryKey(registryKey),
                chest,
                raft
        );
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.BAMBOO_CHEST_RAFT, ModBoats.AZALEA_CHEST_RAFT);
                    itemGroup.addAfter(Items.BAMBOO_CHEST_RAFT, ModBoats.AZALEA_RAFT);
                });

    }
}
