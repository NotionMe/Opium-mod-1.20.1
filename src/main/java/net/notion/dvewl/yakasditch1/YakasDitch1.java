package net.notion.dvewl.yakasditch1;

import net.fabricmc.api.ModInitializer;
import net.notion.dvewl.yakasditch1.block.ModBlocks;
import net.notion.dvewl.yakasditch1.enchantment.ModEnchantments;
import net.notion.dvewl.yakasditch1.item.ModItems;
import net.notion.dvewl.yakasditch1.network.MiningModePacket;
import net.notion.dvewl.yakasditch1.worldgen.ModWorldGen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YakasDitch1 implements ModInitializer {
	public static final String MOD_ID = "yakas-ditch-1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.RegisterModItems();
		ModBlocks.RegisterModBlocks();
		ModWorldGen.registerPlacedFeatures();
		ModEnchantments.registerModEnchantments();
		MiningModePacket.registerServerReceiver();
	}
}