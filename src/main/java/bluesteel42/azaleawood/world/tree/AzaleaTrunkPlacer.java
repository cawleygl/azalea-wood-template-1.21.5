package bluesteel42.azaleawood.world.tree;

import bluesteel42.azaleawood.block.AzaleaStemBlock;
import bluesteel42.azaleawood.block.ModBlocks;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class AzaleaTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<AzaleaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> fillTrunkPlacerFields(instance)
                    .<Integer, IntProvider>and(
                            instance.group(
                                    Codecs.POSITIVE_INT.optionalFieldOf("min_height_for_leaves", 1).forGetter(placer -> placer.minHeightForLeaves),
                                    IntProvider.createValidatingCodec(1, 64).fieldOf("bend_length").forGetter(placer -> placer.bendLength)
                            )
                    )
                    .apply(instance, AzaleaTrunkPlacer::new)
    );
    private final int minHeightForLeaves;
    private final IntProvider bendLength;

    public AzaleaTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, int minHeightForLeaves, IntProvider bendLength) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
        this.minHeightForLeaves = minHeightForLeaves;
        this.bendLength = bendLength;
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTreeBuilders.AZALEA_TRUNK_PLACER;
    }

    private BooleanProperty getPropertyFromDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> AzaleaStemBlock.NORTH;
            case SOUTH -> AzaleaStemBlock.SOUTH;
            case EAST  -> AzaleaStemBlock.EAST;
            case WEST  -> AzaleaStemBlock.WEST;
            case UP    -> AzaleaStemBlock.UP;
            case DOWN  -> AzaleaStemBlock.DOWN;
        };
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config
    ) {
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = height - 1;
        int j = this.bendLength.get(random);
        BlockPos.Mutable mutable = startPos.mutableCopy();
        BlockPos blockPos = mutable.down();
        setToDirt(world, replacer, random, blockPos, config);
        List<BlockPos> placedTrunkBlocks = Lists.<BlockPos>newArrayList();
        List<FoliagePlacer.TreeNode> list = Lists.<FoliagePlacer.TreeNode>newArrayList();

        replacer.accept(mutable.toImmutable(), ModBlocks.AZALEA_STEM.getDefaultState());
        mutable.move(Direction.UP);

        for (int l = 0; l <= i; l++) {
            if (l + 1 >= i + random.nextInt(2)) {
                if (TreeFeature.canReplace(world, mutable)) {
                    placedTrunkBlocks.add(mutable.toImmutable());
                }
                mutable.move(direction);
            }

            if (TreeFeature.canReplace(world, mutable)) {
                placedTrunkBlocks.add(mutable.toImmutable());
            }

            if (l >= this.minHeightForLeaves) {
                list.add(new FoliagePlacer.TreeNode(mutable.toImmutable(), 0, false));
            }

            mutable.move(Direction.UP);
        }

        int bend = placedTrunkBlocks.size();
        for (int k = 0; k <= j; k++) {
            if (TreeFeature.canReplace(world, mutable)) {
                placedTrunkBlocks.add(mutable.toImmutable());
            }
            list.add(new FoliagePlacer.TreeNode(mutable.toImmutable(), 0, false));
            mutable.move(direction);
        }

        int block = 0;

        for (BlockPos pos : placedTrunkBlocks) {
            BlockState state = config.trunkProvider.get(random, pos);

            for (Direction direction2 : Direction.values()) {
                BlockPos neighborPos = pos.offset(direction2);
                if (placedTrunkBlocks.contains(neighborPos)) {
                    BooleanProperty prop = getPropertyFromDirection(direction2);
                    if (state.contains(prop)) {
                        state = state.with(prop, true);
                    }
                }
            }
            if (block >= bend) {
                state = state.with(Properties.AXIS, direction.getAxis());
            }

            replacer.accept(pos, state);
            mutable = pos.mutableCopy();
            this.getAndSetState(world, replacer, random, mutable, config);
            block++;
        }

        return list;
    }
}
