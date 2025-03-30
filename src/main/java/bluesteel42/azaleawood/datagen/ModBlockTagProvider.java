package bluesteel42.azaleawood.datagen;

import bluesteel42.azaleawood.block.ModBlocks;
import bluesteel42.azaleawood.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.AZALEA_STEMS)
                .add(ModBlocks.AZALEA_STEM)
                .add(ModBlocks.STRIPPED_AZALEA_STEM);
        getOrCreateTagBuilder(BlockTags.LOGS).addTag(ModTags.Blocks.AZALEA_STEMS);

        getOrCreateTagBuilder(ModTags.Blocks.AZALEA_BLOCKS)
                .add(ModBlocks.AZALEA_BLOCK)
                .add(ModBlocks.STRIPPED_AZALEA_BLOCK);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).addTag(ModTags.Blocks.AZALEA_BLOCKS);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.AZALEA_MOSAIC);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.AZALEA_MOSAIC_STAIRS);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.AZALEA_MOSAIC_SLAB);

        getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.AZALEA_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.AZALEA_STAIRS);
        getOrCreateTagBuilder(BlockTags.STAIRS).add(ModBlocks.AZALEA_MOSAIC_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.AZALEA_SLAB);
        getOrCreateTagBuilder(BlockTags.SLABS).add(ModBlocks.AZALEA_MOSAIC_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.AZALEA_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.AZALEA_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.AZALEA_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.AZALEA_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.AZALEA_BUTTON);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.AZALEA_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.AZALEA_STANDING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.AZALEA_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.AZALEA_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.AZALEA_WALL_HANGING_SIGN);
    }
}
