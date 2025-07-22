package net.notion.dvewl.yakasditch1.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.notion.dvewl.yakasditch1.YakasDitch1;

public class ModEnchantments {
    public static final Enchantment OPIUM_ENCHANTMENT = Registry.register(
        Registries.ENCHANTMENT,
        new Identifier(YakasDitch1.MOD_ID, "opium_enchantment"),
        new OpiumEnchantment()
    );

    public static void registerModEnchantments() {
        // Викликається у onInitialize
    }
}