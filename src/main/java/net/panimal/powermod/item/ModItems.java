package net.panimal.powermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.panimal.powermod.PowerMod;

public class ModItems {
    public static final Item IRON_FIST = registerItem("iron_fist", new Item(new FabricItemSettings()));
    public static final Item POWER = registerItem("power", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(IRON_FIST);
        entries.add(POWER);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PowerMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PowerMod.LOGGER.info("Registering Mod Items for " + PowerMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
