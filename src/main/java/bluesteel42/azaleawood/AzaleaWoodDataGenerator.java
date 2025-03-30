package bluesteel42.azaleawood;

import bluesteel42.azaleawood.datagen.ModBlockTagProvider;
import bluesteel42.azaleawood.datagen.ModItemTagProvider;
import bluesteel42.azaleawood.datagen.ModLootTableProvider;
import bluesteel42.azaleawood.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AzaleaWoodDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
	}
}
