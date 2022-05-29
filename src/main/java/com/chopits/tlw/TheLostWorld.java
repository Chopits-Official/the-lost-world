package com.chopits.tlw;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.chopits.tlw.RegistryObjects.BLOCK;
import static com.chopits.tlw.RegistryObjects.ITEM;

public class TheLostWorld implements ModInitializer {
	/**
	 * The mod id.
	 */
	public static final String MOD_ID = "the-lost-world";

	/**
	 * The default logger for mod.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ITEM.registry();
		BLOCK.registry();
		LOGGER.info("Hello Traveller!");
	}
}
