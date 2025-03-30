package bluesteel42.azaleawood.block;

import bluesteel42.azaleawood.AzaleaWood;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block AZALEA_STEM = registerBlock(
            "azalea_stem",
            AzaleaStemBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );
    public static final Block STRIPPED_AZALEA_STEM = registerBlock(
            "stripped_azalea_stem",
            AzaleaStemBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_BLOCK = registerBlock(
            "azalea_block",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.BROWN, MapColor.BROWN, BlockSoundGroup.BAMBOO_WOOD)
    );

    public static final Block STRIPPED_AZALEA_BLOCK = registerBlock(
            "stripped_azalea_block",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.TERRACOTTA_PINK, MapColor.TERRACOTTA_PINK, BlockSoundGroup.BAMBOO_WOOD)
    );

    public static final Block AZALEA_PLANKS = registerBlock(
            "azalea_planks",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_MOSAIC = registerBlock(
            "azalea_mosaic",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_STAIRS = registerBlock(
            "azalea_stairs",
            settings -> new StairsBlock(AZALEA_PLANKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(AZALEA_PLANKS)
    );

    public static final Block AZALEA_MOSAIC_STAIRS = registerBlock(
            "azalea_mosaic_stairs",
            settings -> new StairsBlock(AZALEA_MOSAIC.getDefaultState(), settings),
            AbstractBlock.Settings.copy(AZALEA_MOSAIC)
    );

    public static final Block AZALEA_SLAB = registerBlock(
            "azalea_slab",
            SlabBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_MOSAIC_SLAB = registerBlock(
            "azalea_mosaic_slab",
            SlabBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_BUTTON = registerBlock(
            "azalea_button",
            settings -> new ButtonBlock(BlockSetType.BAMBOO, 30, settings),
            AbstractBlock.Settings.create()
                    .noCollision()
                    .strength(0.5F)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block AZALEA_PRESSURE_PLATE = registerBlock(
            "azalea_pressure_plate",
            settings -> new PressurePlateBlock(BlockSetType.BAMBOO, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(0.5F)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block AZALEA_FENCE = registerBlock(
            "azalea_fence",
            FenceBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
    );

    public static final Block AZALEA_FENCE_GATE = registerBlock(
            "azalea_fence_gate",
            settings -> new FenceGateBlock(WoodType.BAMBOO, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .burnable()
    );

    public static final Block AZALEA_DOOR = registerNonOpaqueBlock(
            "azalea_door",
            settings -> new DoorBlock(BlockSetType.BAMBOO, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .nonOpaque()
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block AZALEA_TRAPDOOR = registerNonOpaqueBlock(
            "azalea_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.BAMBOO, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .nonOpaque()
                    .allowsSpawning(Blocks::never)
                    .burnable()
    );

    public static final Identifier AZALEA_SIGN_TEXTURE = Identifier.of(AzaleaWood.MOD_ID, "entity/signs/azalea");
    public static final Identifier AZALEA_HANGING_SIGN_TEXTURE = Identifier.of(AzaleaWood.MOD_ID, "entity/signs/hanging/azalea");
    public static final Identifier AZALEA_HANGING_GUI_SIGN_TEXTURE = Identifier.of(AzaleaWood.MOD_ID, "textures/gui/hanging_signs/azalea");

    public static final Block AZALEA_STANDING_SIGN = registerBlockWithoutItem(
            "azalea_standing_sign",
            settings -> new TerraformSignBlock(AZALEA_SIGN_TEXTURE, settings),
            AbstractBlock.Settings.copy(Blocks.BAMBOO_SIGN)
                    .mapColor(MapColor.TERRACOTTA_PINK)
    );

    public static final Block AZALEA_WALL_SIGN = registerBlockWithoutItem(
            "azalea_wall_sign",
            settings -> new TerraformWallSignBlock(AZALEA_SIGN_TEXTURE, settings),
            AbstractBlock.Settings.copy(Blocks.BAMBOO_SIGN).mapColor(MapColor.TERRACOTTA_PINK).lootTable(AZALEA_STANDING_SIGN.getLootTableKey()).overrideTranslationKey(AZALEA_STANDING_SIGN.getTranslationKey())
    );

    public static final Block AZALEA_HANGING_SIGN = registerBlockWithoutItem(
            "azalea_hanging_sign",
            settings -> new TerraformHangingSignBlock(AZALEA_HANGING_SIGN_TEXTURE, AZALEA_HANGING_GUI_SIGN_TEXTURE, settings),
            AbstractBlock.Settings.copy(Blocks.BAMBOO_HANGING_SIGN)
                    .mapColor(MapColor.TERRACOTTA_PINK)
    );

    public static final Block AZALEA_WALL_HANGING_SIGN = registerBlockWithoutItem(
            "azalea_hanging_wall_sign",
            settings -> new TerraformWallHangingSignBlock(AZALEA_HANGING_SIGN_TEXTURE, AZALEA_HANGING_GUI_SIGN_TEXTURE, settings),
            AbstractBlock.Settings.copy(Blocks.BAMBOO_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_PINK).lootTable(AZALEA_HANGING_SIGN.getLootTableKey()).overrideTranslationKey(AZALEA_HANGING_SIGN.getTranslationKey())
    );

    public static final BlockFamily AZALEA_SIGN_FAMILY = BlockFamilies.register(ModBlocks.AZALEA_PLANKS)
            .sign(ModBlocks.AZALEA_STANDING_SIGN, ModBlocks.AZALEA_WALL_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final BlockFamily AZALEA_HANGING_SIGN_FAMILY = BlockFamilies.register(ModBlocks.STRIPPED_AZALEA_BLOCK)
            .sign(ModBlocks.AZALEA_HANGING_SIGN, ModBlocks.AZALEA_WALL_HANGING_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    private static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(AzaleaWood.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);

        return block;
    }

    private static Block registerNonOpaqueBlock(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(AzaleaWood.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);

        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());

        Items.register(block);

        return block;
    }

    private static Block registerBlockWithoutItem(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(AzaleaWood.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        return Blocks.register(registryKey, factory, settings);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_BUTTON);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_PRESSURE_PLATE);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_TRAPDOOR);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_DOOR);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_FENCE_GATE);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_FENCE);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_MOSAIC_SLAB);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_SLAB);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_MOSAIC_STAIRS);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_STAIRS);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_MOSAIC);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_PLANKS);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.STRIPPED_AZALEA_BLOCK);
                    itemGroup.addAfter(Items.BAMBOO_BUTTON, ModBlocks.AZALEA_BLOCK);
                });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.BAMBOO, ModBlocks.STRIPPED_AZALEA_STEM);
                    itemGroup.addAfter(Items.BAMBOO, ModBlocks.AZALEA_STEM);
                });

    }

}
