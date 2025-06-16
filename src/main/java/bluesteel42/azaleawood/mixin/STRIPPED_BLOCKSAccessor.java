package bluesteel42.azaleawood.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(AxeItem.class)
public interface STRIPPED_BLOCKSAccessor {
    @Accessor("STRIPPED_BLOCKS")
    Map<Block, Block> getSTRIPPED_BLOCKS();
}
