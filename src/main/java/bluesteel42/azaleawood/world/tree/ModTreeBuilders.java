package bluesteel42.azaleawood.world.tree;

import bluesteel42.azaleawood.AzaleaWood;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModTreeBuilders {
    public static final TrunkPlacerType<AzaleaTrunkPlacer> AZALEA_TRUNK_PLACER =
            Registry.register(
                    Registries.TRUNK_PLACER_TYPE,
                    Identifier.of(AzaleaWood.MOD_ID, "azalea_trunk_placer"),
                    new TrunkPlacerType<>(AzaleaTrunkPlacer.CODEC)
            );

    public static void initialize() {

    }
}
