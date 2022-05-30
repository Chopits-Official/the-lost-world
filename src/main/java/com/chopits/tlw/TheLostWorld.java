package com.chopits.tlw;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
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

	public static final Identifier OVERLAY_TEXTURE = new Identifier(MOD_ID, "textures/gui/overlay.png");
	public static final TrackedData<Float> MAGIC = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.FLOAT);
	public static final TrackedData<Integer> TALENT_LEVEL = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> STR = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> DEX = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> MYS = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> CON = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> BEL = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);



	@Override
	public void onInitialize() {
		ITEM.registerAll();
		BLOCK.registerAll();
		LOGGER.info("Hello Traveller!");
	}
}
