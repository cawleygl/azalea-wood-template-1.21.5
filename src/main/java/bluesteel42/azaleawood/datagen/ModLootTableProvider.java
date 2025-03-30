package bluesteel42.azaleawood.datagen;

import bluesteel42.azaleawood.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.AZALEA_STEM);
        addDrop(ModBlocks.STRIPPED_AZALEA_STEM);

        addDrop(ModBlocks.AZALEA_BLOCK);
        addDrop(ModBlocks.STRIPPED_AZALEA_BLOCK);

        addDrop(ModBlocks.AZALEA_PLANKS);
        addDrop(ModBlocks.AZALEA_MOSAIC);
        addDrop(ModBlocks.AZALEA_STAIRS);
        addDrop(ModBlocks.AZALEA_MOSAIC_STAIRS);
        addDrop(ModBlocks.AZALEA_BUTTON);
        addDrop(ModBlocks.AZALEA_FENCE_GATE);
        addDrop(ModBlocks.AZALEA_FENCE);
        addDrop(ModBlocks.AZALEA_PRESSURE_PLATE);
        addDrop(ModBlocks.AZALEA_TRAPDOOR);
        addDrop(ModBlocks.AZALEA_DOOR, doorDrops(ModBlocks.AZALEA_DOOR));
        addDrop(ModBlocks.AZALEA_SLAB, slabDrops(ModBlocks.AZALEA_SLAB));
        addDrop(ModBlocks.AZALEA_MOSAIC_SLAB, slabDrops(ModBlocks.AZALEA_MOSAIC_SLAB));

        addDrop(ModBlocks.AZALEA_STANDING_SIGN);
        addDrop(ModBlocks.AZALEA_WALL_SIGN);
        addDrop(ModBlocks.AZALEA_HANGING_SIGN);
        addDrop(ModBlocks.AZALEA_WALL_HANGING_SIGN);
    }
}
