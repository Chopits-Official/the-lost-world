package com.chopits.tlw;

import com.chopits.tlw.util.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.chopits.tlw.TheLostWorld.MOD_ID;

public class RegistryObjects {
    public static final RegistryHelper<Item> ITEM = new RegistryHelper<>(Registry.ITEM, MOD_ID);
    public static final RegistryHelper<Block> BLOCK = new RegistryHelper<>(Registry.BLOCK, MOD_ID);
}
