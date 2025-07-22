package net.notion.dvewl.yakasditch1.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.notion.dvewl.yakasditch1.enchantment.ModEnchantments;
import net.minecraft.block.BlockState;

public class OpiumPickaxe_level1 extends MineEnchantment {

    public OpiumPickaxe_level1(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        stack.addEnchantment(ModEnchantments.OPIUM_ENCHANTMENT, 1); // 1 — рівень зачарки
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return super.getMiningSpeedMultiplier(stack, state) * 1.0f;
    }
}
