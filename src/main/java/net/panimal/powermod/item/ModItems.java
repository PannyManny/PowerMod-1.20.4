package net.panimal.powermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.panimal.powermod.PowerMod;
import net.panimal.powermod.item.custom.CoreItem;
import net.panimal.powermod.item.custom.LaserItem;

public class ModItems {
    public static final Item IRON_FIST = registerItem("iron_fist",
            new SwordItem(ModToolMaterial.IRON_FIST, 20,2, new FabricItemSettings()));

    public static final Item POWER = registerItem("power", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item LASER = registerItem("laser",
            new LaserItem(new FabricItemSettings().maxCount(1)));

    public static final Item HAMMER = registerItem("hammer", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item METEOR = registerItem("meteor", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item BLACK_HOLE = registerItem("black_hole",
            new Item(new FabricItemSettings().maxCount(1)));

    public static final Item CORE = registerItem("core",
            new CoreItem(new FabricItemSettings().maxCount(1)));

    public static final Item GALAXY = registerItem("galaxy", new Item(new FabricItemSettings().maxCount(1)));

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
