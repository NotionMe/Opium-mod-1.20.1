package net.notion.dvewl.yakasditch1.item;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraft.enchantment.EnchantmentHelper;
import net.notion.dvewl.yakasditch1.enchantment.ModEnchantments;

public class OpiumPickaxe extends PickaxeItem {
    public OpiumPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        stack.addEnchantment(ModEnchantments.OPIUM_ENCHANTMENT, 0);
    }

    @Override
public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    if (!world.isClient && miner instanceof PlayerEntity player) {
        int level = EnchantmentHelper.getLevel(ModEnchantments.OPIUM_ENCHANTMENT, stack);
        if (level > 0) {
            int radius = level - 1; // 1 -> 3x3, 2 -> 5x5, 3 -> 7x7 ...
            double dx = Math.abs(player.getX() - (pos.getX() + 0.5));
            double dy = Math.abs(player.getEyeY() - (pos.getY() + 0.5));
            double dz = Math.abs(player.getZ() - (pos.getZ() + 0.5));
            if (dx >= dy && dx >= dz) {
                // YZ площина
                for (int dy_ = -radius; dy_ <= radius; dy_++) {
                    for (int dz_ = -radius; dz_ <= radius; dz_++) {
                        BlockPos newPos = pos.add(0, dy_, dz_);
                        if (!newPos.equals(pos)) {
                            BlockState targetState = world.getBlockState(newPos);
                            if (targetState.getHardness(world, newPos) >= 0 && !targetState.isAir()) {
                                world.breakBlock(newPos, true, miner);
                            }
                        }
                    }
                }
            } else if (dy >= dx && dy >= dz) {
                // XZ площина
                for (int dx_ = -radius; dx_ <= radius; dx_++) {
                    for (int dz_ = -radius; dz_ <= radius; dz_++) {
                        BlockPos newPos = pos.add(dx_, 0, dz_);
                        if (!newPos.equals(pos)) {
                            BlockState targetState = world.getBlockState(newPos);
                            if (targetState.getHardness(world, newPos) >= 0 && !targetState.isAir()) {
                                world.breakBlock(newPos, true, miner);
                            }
                        }
                    }
                }
            } else {
                // XY площина
                for (int dx_ = -radius; dx_ <= radius; dx_++) {
                    for (int dy_ = -radius; dy_ <= radius; dy_++) {
                        BlockPos newPos = pos.add(dx_, dy_, 0);
                        if (!newPos.equals(pos)) {
                            BlockState targetState = world.getBlockState(newPos);
                            if (targetState.getHardness(world, newPos) >= 0 && !targetState.isAir()) {
                                world.breakBlock(newPos, true, miner);
                            }
                        }
                    }
                }
            }
        }
    }
    return super.postMine(stack, world, state, pos, miner);
}
}