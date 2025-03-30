package bluesteel42.azaleawood;

import bluesteel42.azaleawood.entity.ModBoats;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;

public class AzaleaWoodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register Boat Model
        TerraformBoatClientHelper.registerModelLayers(ModBoats.AZALEA_RAFTS_ID);
    }
}
