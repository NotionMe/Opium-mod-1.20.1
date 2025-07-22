package net.notion.dvewl.yakasditch1.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.notion.dvewl.yakasditch1.item.ModItems;

public class OpiumEnchantment extends Enchantment {

    public OpiumEnchantment() {
        super(
                Rarity.UNCOMMON, // Рідкість зачарки
                EnchantmentTarget.WEAPON, // можна змінити: ARMOR, ARMOR_FEET, BOW, etc.
                new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() == ModItems.OPIUM_PICKAXE;
    }

    @Override
    public int getMaxLevel() {
        return 5; // максимальний рівень зачарки
    }

    @Override
    public boolean isTreasure() {
        return false; // якщо true — тільки у скарбах
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true; // доступна через села
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true; // доступна в таблиці зачарування
    }

}
