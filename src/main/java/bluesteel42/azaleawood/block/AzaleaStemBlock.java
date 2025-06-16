package bluesteel42.azaleawood.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class AzaleaStemBlock extends PillarBlock implements Waterloggable {
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ImmutableMap.copyOf(
            Maps.newEnumMap(Map.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.SOUTH, SOUTH, Direction.WEST, WEST, Direction.UP, UP, Direction.DOWN, DOWN))
    );
    public static final MapCodec<AzaleaStemBlock> CODEC = createCodec(AzaleaStemBlock::new);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final Function<BlockState, VoxelShape> shapeFunction;

    @Override
    public MapCodec<? extends AzaleaStemBlock> getCodec() {
        return CODEC;
    }

    public AzaleaStemBlock(Settings settings) {
        super(settings);
        this.shapeFunction = this.createShapeFunction(6.0F);
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
        BlockState state = super.getPlacementState(ctx)
                .with(NORTH, this.canConnect(blockState))
                .with(EAST, this.canConnect(blockState2))
                .with(SOUTH, this.canConnect(blockState3))
                .with(WEST, this.canConnect(blockState4))
                .with(UP, this.canConnect(blockState5))
                .with(DOWN, this.canConnect(blockState6))
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        return state;
    }

    private Function<BlockState, VoxelShape> createShapeFunction(float radius) {
        VoxelShape voxelShape = Block.createCubeShape(radius);
        Map<Direction, VoxelShape> map = VoxelShapes.createFacingShapeMap(Block.createCuboidZShape(radius, 0.0, 8.0));
        return this.createShapeFunction(state -> {
            VoxelShape voxelShape2 = voxelShape;

            for (Map.Entry<Direction, BooleanProperty> entry : FACING_PROPERTIES.entrySet()) {
                if ((Boolean)state.get((Property)entry.getValue())) {
                    voxelShape2 = VoxelShapes.union((VoxelShape)map.get(entry.getKey()), voxelShape2);
                }
            }
            if (state.get(Properties.AXIS).equals(Direction.Axis.X)) {
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.WEST), voxelShape2);
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.EAST), voxelShape2);
            }
            if (state.get(Properties.AXIS).equals(Direction.Axis.Y)) {
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.UP), voxelShape2);
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.DOWN), voxelShape2);
            }
            if (state.get(Properties.AXIS).equals(Direction.Axis.Z)) {
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.NORTH), voxelShape2);
                voxelShape2 = VoxelShapes.union((VoxelShape)map.get(Direction.SOUTH), voxelShape2);
            }

            return voxelShape2;
        });
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape)this.shapeFunction.apply(state);
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
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(true) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, AXIS, WATERLOGGED);
    }
}