package net.notion.dvewl.yakasditch1.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.notion.dvewl.yakasditch1.enchantment.ModEnchantments;
import net.minecraft.block.BlockState;

public class OpiumPickaxe extends MineEnchantment {

    public OpiumPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        stack.addEnchantment(ModEnchantments.OPIUM_ENCHANTMENT, 10); // Set the enchantment level to 10
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return super.getMiningSpeedMultiplier(stack, state) * 5.0f; // Increase mining speed significantly
    }
}