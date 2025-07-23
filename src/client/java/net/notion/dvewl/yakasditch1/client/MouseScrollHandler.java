package net.notion.dvewl.yakasditch1.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.notion.dvewl.yakasditch1.item.MineEnchantment;
import net.notion.dvewl.yakasditch1.network.MiningModePacket;
import org.lwjgl.glfw.GLFW;

public class MouseScrollHandler {
    private static KeyBinding changeMiningModeKey;

    public static void register() {
        // Реєструємо клавішу для зміни режиму копання
        changeMiningModeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.yakasditch1.change_mining_mode",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V, // Клавіша V
            "category.yakasditch1.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.currentScreen == null) {
                handleKeyInput(client);
            }
        });
    }

    private static void handleKeyInput(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) return;

        // Перевіряємо чи натиснуто клавішу зміни режиму
        if (changeMiningModeKey.wasPressed()) {
            ItemStack mainHandStack = player.getMainHandStack();
            if (mainHandStack.getItem() instanceof MineEnchantment) {
                // Відправляємо пакет на сервер для зміни режиму
                ClientPlayNetworking.send(MiningModePacket.CHANGE_MINING_MODE, PacketByteBufs.create());
            }
        }
    }
}