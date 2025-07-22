package net.notion.dvewl.yakasditch1.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.notion.dvewl.yakasditch1.YakasDitch1;

public class ModBlocks {
    public static final Block OPIUM_ORE = registerBlock("opium_ore",
            new Block(FabricBlockSettings.create()
            .strength(3.0f, 3.0f)
            .requiresTool()));

    public static final Block CUSTOM_ENCHANTING_TABLE = registerBlock("custom_enchanting_table",
    new CustomEnchantingTableBlock(FabricBlockSettings.create().strength(4.0f)));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(YakasDitch1.MOD_ID, name), block);
        Registry.register(Registries.ITEM, new Identifier(YakasDitch1.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
        return block;
    }

    public static void RegisterModBlocks() {
        System.out.println("Registering opium ore block: " + OPIUM_ORE);
        System.out.println("Block registered with ID: yakas-ditch-1:opium_ore");
    }
}