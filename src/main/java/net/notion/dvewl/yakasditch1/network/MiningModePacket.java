package net.notion.dvewl.yakasditch1.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.notion.dvewl.yakasditch1.YakasDitch1;
import net.notion.dvewl.yakasditch1.item.MineEnchantment;

public class MiningModePacket {
    public static final Identifier CHANGE_MINING_MODE = new Identifier(YakasDitch1.MOD_ID, "change_mining_mode");

    public static void registerServerReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(CHANGE_MINING_MODE, (server, player, handler, buf, responseSender) -> extracted(server, player));
    }

    private static void extracted(MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            ItemStack mainHandStack = player.getMainHandStack();
            if (mainHandStack.getItem() instanceof MineEnchantment) {
                MineEnchantment.cycleMiningSize(mainHandStack, player);
            }
        });
    }
}