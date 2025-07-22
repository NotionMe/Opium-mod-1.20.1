package net.notion.dvewl.yakasditch1.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGen {
    public static final RegistryKey<PlacedFeature> OPIUM_ORE_PLACED = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier("yakas-ditch-1", "opium_ore"));

            public static void registerPlacedFeatures() {
                System.out.println("Registering opium ore generation!");
                System.out.println("OPIUM_ORE_PLACED key: " + OPIUM_ORE_PLACED.getValue());
                
                // Додаємо руду до всіх біомів Overworld (без дублювання)
                BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                        GenerationStep.Feature.UNDERGROUND_ORES, OPIUM_ORE_PLACED);
                        
                System.out.println("Opium ore generation registered successfully!");
            }
            
}
