package bluesteel42.azaleawood.mixin;

import bluesteel42.azaleawood.AzaleaWood;
import bluesteel42.azaleawood.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class StripBlockStateMixin {

    @Inject(at = @At("HEAD"), method = "getStrippedState", cancellable = true)
    private void init(
            BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir
    ) {
        if (state.isOf(ModBlocks.AZALEA_STEM) || state.isOf(ModBlocks.LEAFY_AZALEA_STEM)) {
            STRIPPED_BLOCKSAccessor strippedBlocksAccessor = (STRIPPED_BLOCKSAccessor) (Object) this;
            Optional<BlockState> strippedState = Optional.ofNullable((Block) strippedBlocksAccessor.getSTRIPPED_BLOCKS().get(state.getBlock()))
                    .map(block -> {
                        BlockState strippedBlock = block.getDefaultState()
                                .with(PillarBlock.AXIS, (Direction.Axis) state.get(PillarBlock.AXIS))
                                .with(Properties.NORTH, state.get(Properties.NORTH))
                                .with(Properties.SOUTH, state.get(Properties.SOUTH))
                                .with(Properties.EAST, state.get(Properties.EAST))
                                .with(Properties.WEST, state.get(Properties.WEST))
                                .with(Properties.UP, state.get(Properties.UP))
                                .with(Properties.DOWN, state.get(Properties.DOWN))
                                .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED));

                        strippedBlock.with(Properties.NORTH, state.get(Properties.NORTH));
                        strippedBlock.with(Properties.SOUTH, state.get(Properties.SOUTH));
                        strippedBlock.with(Properties.EAST, state.get(Properties.EAST));
                        strippedBlock.with(Properties.WEST, state.get(Properties.WEST));
                        strippedBlock.with(Properties.UP, state.get(Properties.UP));
                        strippedBlock.with(Properties.DOWN, state.get(Properties.DOWN));
                        strippedBlock.with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED));

                        return strippedBlock;
                    });

            cir.setReturnValue(strippedState);
            cir.cancel();
        }
    }
}
