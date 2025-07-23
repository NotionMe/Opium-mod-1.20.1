package net.notion.dvewl.yakasditch1.item;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.notion.dvewl.yakasditch1.enchantment.ModEnchantments;

import java.util.List;

public class MineEnchantment extends PickaxeItem {
    private static final String MINING_SIZE_KEY = "MiningSize";

    public MineEnchantment(ToolMaterial material, int attackDamage, float attackSpeed, PickaxeItem.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    // Отримати поточний розмір копання
    public static int getMiningSize(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains(MINING_SIZE_KEY)) {
            return nbt.getInt(MINING_SIZE_KEY);
        }
        return 1; // За замовчуванням 1x1
    }

    // Встановити розмір копання
    public static void setMiningSize(ItemStack stack, int size) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(MINING_SIZE_KEY, Math.max(0, size)); // Мінімум 0 (вимкнено)
    }

    // Змінити розмір копання
    public static void cycleMiningSize(ItemStack stack, PlayerEntity player) {
        int enchantLevel = EnchantmentHelper.getLevel(ModEnchantments.OPIUM_ENCHANTMENT, stack);
        if (enchantLevel > 0) {
            int currentSize = getMiningSize(stack);
            int newSize = (currentSize + 1) % (enchantLevel + 1); // 0, 1, 2, ..., enchantLevel
            setMiningSize(stack, newSize);

            String sizeText = newSize == 0 ? "Вимкнено" : (newSize * 2 + 1) + "x" + (newSize * 2 + 1);
            player.sendMessage(Text.literal("Розмір копання: " + sizeText), true);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        int enchantLevel = EnchantmentHelper.getLevel(ModEnchantments.OPIUM_ENCHANTMENT, stack);
        if (enchantLevel > 0) {
            int miningSize = getMiningSize(stack);
            String sizeText = miningSize == 0 ? "Disabled" : (miningSize * 2 + 1) + "x" + (miningSize * 2 + 1);
            tooltip.add(Text.literal("Mining mode: " + sizeText).formatted(Formatting.GRAY));
            tooltip.add(Text.literal("Press V to change mode").formatted(Formatting.DARK_GRAY));
        }
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && miner instanceof PlayerEntity player) {
            int level = EnchantmentHelper.getLevel(ModEnchantments.OPIUM_ENCHANTMENT, stack);
            if (level > 0) {
                int miningSize = getMiningSize(stack);
                if (miningSize == 0)
                    return super.postMine(stack, world, state, pos, miner); // Вимкнено

                int radius = miningSize; // Використовуємо збережений розмір
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
