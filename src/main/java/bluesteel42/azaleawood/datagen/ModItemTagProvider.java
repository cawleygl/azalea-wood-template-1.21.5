package bluesteel42.azaleawood.datagen;

import bluesteel42.azaleawood.block.ModBlocks;
import bluesteel42.azaleawood.entity.ModBoats;
import bluesteel42.azaleawood.item.ModItems;
import bluesteel42.azaleawood.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.AZALEA_STEMS)
                .add(ModBlocks.AZALEA_STEM.asItem())
                .add(ModBlocks.STRIPPED_AZALEA_STEM.asItem());
        getOrCreateTagBuilder(ItemTags.LOGS).addTag(ModTags.Items.AZALEA_STEMS);

        getOrCreateTagBuilder(ModTags.Items.AZALEA_BLOCKS)
                .add(ModBlocks.AZALEA_BLOCK.asItem())
                .add(ModBlocks.STRIPPED_AZALEA_BLOCK.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS).add(ModBlocks.AZALEA_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(ModBlocks.AZALEA_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS).add(ModBlocks.AZALEA_MOSAIC_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(ModBlocks.AZALEA_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(ModBlocks.AZALEA_MOSAIC_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(ModBlocks.AZALEA_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(ModBlocks.AZALEA_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(ModBlocks.AZALEA_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(ModBlocks.AZALEA_FENCE_GATE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(ModBlocks.AZALEA_BUTTON.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.AZALEA_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS).add(ModItems.AZALEA_SIGN);
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(ModItems.AZALEA_HANGING_SIGN);

        getOrCreateTagBuilder(ItemTags.BOATS).add(ModBoats.AZALEA_RAFT.asItem());
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(ModBoats.AZALEA_CHEST_RAFT.asItem());
    }
}
