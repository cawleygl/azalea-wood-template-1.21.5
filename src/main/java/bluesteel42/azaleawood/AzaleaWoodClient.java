package bluesteel42.azaleawood;

import bluesteel42.azaleawood.block.ModBlocks;
import bluesteel42.azaleawood.entity.ModBoats;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AzaleaWoodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register Boat Model
        TerraformBoatClientHelper.registerModelLayers(ModBoats.AZALEA_RAFTS_ID);
        // Transparent Leafy block
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEAFY_AZALEA_STEM, RenderLayer.getCutoutMipped());
    }
}
