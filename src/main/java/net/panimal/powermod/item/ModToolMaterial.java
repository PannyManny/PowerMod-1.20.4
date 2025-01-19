package net.panimal.powermod.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    IRON_FIST(1,1531, 1, 20, 26,
            () -> Ingredient.ofItems(ModItems.IRON_FIST));

    private final int miningLevel;
    private final int itemDurability;
    private final int miningSpeed;
    private final int attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngrediant;

    ModToolMaterial(int miningLevel, int itemDurability, int miningSpeed, int attackDamage, int enchantability, Supplier<Ingredient> repairIngrediant) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngrediant = repairIngrediant;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningSpeed;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngrediant.get();
    }
}
