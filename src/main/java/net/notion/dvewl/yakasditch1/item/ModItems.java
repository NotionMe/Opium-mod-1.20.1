package net.notion.dvewl.yakasditch1.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ArmorItem;
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

        public static final Item OPIUM_PICKAXE_LEVEL3 = register("opium_pickaxe_level3",
                        settings -> new OpiumPickaxe_level3(OpiumToolMaterial.INSTANCE, 1, -2.8f, settings),
                        new Item.Settings());

        public static final Item ENDER_NETHERITE_HELMET = registerItem("ender_netherite_helmet",
                        new ArmorItem(ModArmorMaterials.ENDER_NETHERITE, ArmorItem.Type.HELMET,
                                        new FabricItemSettings()));
        public static final Item ENDER_NETHERITE_CHESPLATE = registerItem("ender_netherite_chestplate",
                        new ArmorItem(ModArmorMaterials.ENDER_NETHERITE, ArmorItem.Type.CHESTPLATE,
                                        new FabricItemSettings()));
        public static final Item ENDER_NETHERITE_LEGGINGS = registerItem("ender_netherite_leggings",
                        new ArmorItem(ModArmorMaterials.ENDER_NETHERITE, ArmorItem.Type.LEGGINGS,
                                        new FabricItemSettings()));
        public static final Item ENDER_NETHERITE_BOOTS = registerItem("ender_netherite_boots",
                        new ArmorItem(ModArmorMaterials.ENDER_NETHERITE, ArmorItem.Type.BOOTS,
                                        new FabricItemSettings()));

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
                                                entries.add(OPIUM_PICKAXE_LEVEL3);
                                                entries.add(ENDER_NETHERITE_HELMET);
                                                entries.add(ENDER_NETHERITE_CHESPLATE);
                                                entries.add(ENDER_NETHERITE_LEGGINGS);
                                                entries.add(ENDER_NETHERITE_BOOTS);
                                        })
                                        .build());

        public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
                Identifier itemId = Identifier.of(YakasDitch1.MOD_ID, name);

                Item item = itemFactory.apply(settings);
                return Registry.register(Registries.ITEM, itemId, item);
        }

        private static Item registerItem(String name, Item item) {
                return Registry.register(Registries.ITEM, new Identifier(YakasDitch1.MOD_ID, name), item);
        }

        public static void RegisterModItems() {
                // Просто викликається в onInitialize
        }
}
