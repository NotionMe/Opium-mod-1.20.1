package net.notion.dvewl.yakasditch1.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class OpiumToolMaterial implements ToolMaterial {

    public static final OpiumToolMaterial INSTANCE = new OpiumToolMaterial();

    @Override
    public int getDurability() {
        return 512; // довговічність
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0f; // швидкість копання
    }

    @Override
    public float getAttackDamage() {
        return 3.0f; // базова атака
    }

    @Override
    public int getMiningLevel() {
        return 2; // рівень добування (як IRON)
    }

    @Override
    public int getEnchantability() {
        return 18; // енчантованість
    }

    @Override
    public Ingredient getRepairIngredient() {
        // Replace Items.WHEAT with your actual repair item, e.g., ModItems.OPIUM_ORE if available
        return Ingredient.ofItems(ModItems.OPIUM_INGOT);
    }
}
