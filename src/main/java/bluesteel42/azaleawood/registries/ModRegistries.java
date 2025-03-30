package bluesteel42.azaleawood.registries;

import bluesteel42.azaleawood.block.ModBlocks;
import bluesteel42.azaleawood.entity.ModBoats;
import bluesteel42.azaleawood.util.ModTags;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class ModRegistries {
    public static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.AZALEA_STEM, ModBlocks.STRIPPED_AZALEA_STEM);
        StrippableBlockRegistry.register(ModBlocks.AZALEA_BLOCK, ModBlocks.STRIPPED_AZALEA_BLOCK);
    }

    public static void registerFlammables() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_STEM, 60, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_AZALEA_STEM, 60, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_MOSAIC, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_MOSAIC_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_FENCE_GATE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_MOSAIC_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.AZALEA_BLOCK, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_AZALEA_BLOCK, 5, 5);
    }

    public static void registerFuels() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.AZALEA_STEM, 50);
            builder.add(ModBlocks.STRIPPED_AZALEA_STEM, 50);
            builder.add(ModTags.Items.AZALEA_BLOCKS, 300);
            builder.add(ModBlocks.AZALEA_MOSAIC, 300);
            builder.add(ModBlocks.AZALEA_MOSAIC_SLAB, 150);
            builder.add(ModBlocks.AZALEA_MOSAIC_STAIRS, 300);
        });
    }

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(ModBoats.AZALEA_RAFT, 1),
                            new ItemStack(Items.EMERALD, 1),
                            12,  30, 0.05f));
                });
    }
}
