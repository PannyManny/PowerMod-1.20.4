package net.panimal.powermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.panimal.powermod.PowerMod;

public class ModItemGroups {
    private static final ItemGroup POWER_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(PowerMod.MOD_ID, "power"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.power"))
                    .icon(() -> new ItemStack(ModItems.POWER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.IRON_FIST);
                        entries.add(ModItems.POWER);
                        entries.add(ModItems.LASER);
                        entries.add(ModItems.HAMMER);
                        entries.add(ModItems.METEOR);
                        entries.add(ModItems.BLACK_HOLE);
                        entries.add(ModItems.CORE);
                        entries.add(ModItems.GALAXY);

                    }).build());

    public static void registerItemGroups() {
        PowerMod.LOGGER.info("Registering Item Groups for " + PowerMod.MOD_ID);
    }
}
