package bluesteel42.azaleawood.util;

import bluesteel42.azaleawood.AzaleaWood;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> AZALEA_STEMS = createTag("azalea_stems");
        public static final TagKey<Block> AZALEA_BLOCKS = createTag("azalea_blocks");

        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AzaleaWood.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AZALEA_STEMS = createTag("azalea_stems");
        public static final TagKey<Item> AZALEA_BLOCKS = createTag("azalea_blocks");

        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AzaleaWood.MOD_ID, name));
        }
    }
}
