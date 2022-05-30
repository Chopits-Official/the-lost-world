package com.chopits.tlw;

import com.chopits.tlw.util.item.trinkets.ring.Ring;
import com.chopits.tlw.util.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class RegistryObjects {
    public static final RegistryHelper<Item> ITEM = new RegistryHelper<>(Registry.ITEM);
    public static final RegistryHelper<Block> BLOCK = new RegistryHelper<>(Registry.BLOCK);

    //Items
    public static final RegistryHelper.RegistryObject<Item> RING = ITEM.register("ring",() -> new Ring(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1)));
}
