package bluesteel42.azaleawood.mixin;

import bluesteel42.azaleawood.block.ModBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearLeafyAzaleaStemMixin {
	@Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
	private void init(
			ItemUsageContext context,
			CallbackInfoReturnable<ActionResult> cir
	) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.isOf(ModBlocks.LEAFY_AZALEA_STEM)) {
			PlayerEntity playerEntity = context.getPlayer();
			ItemStack itemStack = context.getStack();
			if (playerEntity instanceof ServerPlayerEntity) {
				Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
			}

			world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);

			BlockState blockState2 = ModBlocks.AZALEA_STEM.getDefaultState()
					.with(Properties.NORTH, blockState.get(Properties.NORTH))
					.with(Properties.EAST, blockState.get(Properties.EAST))
					.with(Properties.SOUTH, blockState.get(Properties.SOUTH))
					.with(Properties.WEST, blockState.get(Properties.WEST))
					.with(Properties.UP, blockState.get(Properties.UP))
					.with(Properties.DOWN, blockState.get(Properties.DOWN))
					.with(Properties.AXIS, blockState.get(Properties.AXIS))
					.with(Properties.WATERLOGGED, blockState.get(Properties.WATERLOGGED));
			world.setBlockState(blockPos, blockState2, 1);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(context.getPlayer(), blockState2));
			if (playerEntity != null) {
				itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
			}

			cir.setReturnValue(ActionResult.SUCCESS);
			cir.cancel();
		}
	}
}