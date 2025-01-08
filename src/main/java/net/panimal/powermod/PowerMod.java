package net.panimal.powermod;

import net.fabricmc.api.ModInitializer;

import net.panimal.powermod.item.ModItemGroups;
import net.panimal.powermod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PowerMod implements ModInitializer {
	public static final String MOD_ID = "powermod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}