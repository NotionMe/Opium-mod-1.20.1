package net.notion.dvewl.yakasditch1.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.notion.dvewl.yakasditch1.YakasDitch1;
import net.notion.dvewl.yakasditch1.block.ModBlocks;

public class ModItems {

        public static final Item OPIUM_INGOT = register("opium_ingot",
                        Item::new, new Item.Settings());

        public static final Item RAW_OPIUM = register("raw_opium",
                        Item::new, new Item.Settings());

        public static final Item OPIUM_SWORD = register("opium_sword",
                        settings -> new OpiumSword(OpiumToolMaterial.INSTANCE, 6, -1.4f, settings),
                        new Item.Settings());

        public static final Item OPIUM_PICKAXE = register("opium_pickaxe",
                        settings -> new OpiumPickaxe(OpiumToolMaterial.INSTANCE, 1, -2.8f, settings),
                        new Item.Settings());

        public static final Item OPIUM_PICKAXE_LEVEL1 = register("opium_pickaxe_level1",
                        settings -> new OpiumPickaxe_level1(OpiumToolMaterial.INSTANCE, 1, -2.8f, settings),
                        new Item.Settings());

        public static final Item OPIUM_PICKAXE_LEVEL2 = register("opium_pickaxe_level2",
                        settings -> new OpiumPickaxe_level2(OpiumToolMaterial.INSTANCE, 1, -2.8f, settings),
                        new Item.Settings());

        // Item Group
        public static final ItemGroup YAKASDITCH_GROUP = Registry.register(
                        Registries.ITEM_GROUP,
                        new Identifier(YakasDitch1.MOD_ID, "yakasditch_tab"),
                        FabricItemGroup.builder()
                                        .icon(() -> new ItemStack(OPIUM_INGOT))
                                        .displayName(Text.translatable("itemGroup.yakasditch_tab"))
                                        .entries((displayContext, entries) -> {
                                                entries.add(OPIUM_INGOT);
                                                entries.add(RAW_OPIUM);
                                                entries.add(ModBlocks.OPIUM_ORE);
                                                entries.add(OPIUM_SWORD);
                                                entries.add(OPIUM_PICKAXE);
                                                entries.add(OPIUM_PICKAXE_LEVEL1);
                                                entries.add(OPIUM_PICKAXE_LEVEL2);
                                        })
                                        .build());

        public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
                Identifier itemId = Identifier.of(YakasDitch1.MOD_ID, name);

                Item item = itemFactory.apply(settings);
                return Registry.register(Registries.ITEM, itemId, item);
        }

        public static void RegisterModItems() {
                // Просто викликається в onInitialize
        }
}
