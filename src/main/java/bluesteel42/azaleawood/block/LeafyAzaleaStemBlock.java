package bluesteel42.azaleawood.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class LeafyAzaleaStemBlock extends PillarBlock implements Waterloggable {
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ImmutableMap.copyOf(
            Maps.newEnumMap(Map.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST, Direction.UP, UP, Direction.DOWN, DOWN))
    );
    public static final MapCodec<LeafyAzaleaStemBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codecs.rangedInclusiveFloat(0.0F, 1.0F)
                                    .fieldOf("leaf_particle_chance")
                                    .forGetter(leafyAzaleaStemBlock -> leafyAzaleaStemBlock.leafParticleChance),
                            ParticleTypes.TYPE_CODEC.fieldOf("leaf_particle").forGetter(leafyAzaleaStemBlock -> leafyAzaleaStemBlock.leafParticleEffect),
                            createSettingsCodec()
                    )
                    .apply(instance, LeafyAzaleaStemBlock::new)
    );
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected final ParticleEffect leafParticleEffect;
    protected final float leafParticleChance;

    @Override
    public MapCodec<LeafyAzaleaStemBlock> getCodec() {
        return CODEC;
    }

    public LeafyAzaleaStemBlock(float leafParticleChance, ParticleEffect leafParticleEffect, Settings settings) {
        super(settings);
        this.leafParticleChance = leafParticleChance;
        this.leafParticleEffect = leafParticleEffect;
        this.setDefaultState(
                this.getDefaultState()
                        .with(NORTH, false)
                        .with(EAST, false)
                        .with(SOUTH, false)
                        .with(WEST, false)
                        .with(UP, false)
                        .with(DOWN, false)
                        .with(WATERLOGGED, false)
        );
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    protected int getOpacity(BlockState state) {
        return 1;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        spawnWaterParticle(world, pos, random, blockState, blockPos);
        this.spawnLeafParticle(world, pos, random, blockState, blockPos);
    }

    private static void spawnWaterParticle(World world, BlockPos pos, Random random, BlockState state, BlockPos posBelow) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                if (!state.isOpaque() || !state.isSideSolidFullSquare(world, posBelow, Direction.UP)) {
                    ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER);
                }
            }
        }
    }

    private void spawnLeafParticle(World world, BlockPos pos, Random random, BlockState state, BlockPos posBelow) {
        if (!(random.nextFloat() >= this.leafParticleChance)) {
            if (!isFaceFullSquare(state.getCollisionShape(world, posBelow), Direction.UP)) {
                this.spawnLeafParticle(world, pos, random);
            }
        }
    }

    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        ParticleUtil.spawnParticle(world, pos, random, this.leafParticleEffect);
    }

    public boolean canConnect(BlockState state) {
        return state.isOf(ModBlocks.AZALEA_STEM) || state.isOf(ModBlocks.LEAFY_AZALEA_STEM) || state.isOf(ModBlocks.STRIPPED_AZALEA_STEM) ;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.up();
        BlockPos blockPos7 = blockPos.down();
        BlockState blockState = blockView.getBlockState(blockPos2);
        BlockState blockState2 = blockView.getBlockState(blockPos3);
        BlockState blockState3 = blockView.getBlockState(blockPos4);
        BlockState blockState4 = blockView.getBlockState(blockPos5);
        BlockState blockState5 = blockView.getBlockState(blockPos6);
        BlockState blockState6 = blockView.getBlockState(blockPos7);
        return super.getPlacementState(ctx)
                .with(NORTH, this.canConnect(blockState))
                .with(EAST, this.canConnect(blockState2))
                .with(SOUTH, this.canConnect(blockState3))
                .with(WEST, this.canConnect(blockState4))
                .with(UP, this.canConnect(blockState5))
                .with(DOWN, this.canConnect(blockState6))
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return state.with(
                (Property)FACING_PROPERTIES.get(direction),
                this.canConnect(neighborState)
        );
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, AXIS, WATERLOGGED);
    }

}
