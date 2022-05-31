package com.chopits.tlw.util.item.trinkets.ring;

import com.chopits.tlw.attributes.Attributes;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

import static com.chopits.tlw.TheLostWorld.MOD_ID;

public class Ring extends TrinketItem {
    public Ring(Settings settings) {
        super(settings);
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +8 max magic
        modifiers.put(Attributes.GENERIC_MAX_MAGIC, new EntityAttributeModifier(uuid, MOD_ID + ":max_magic", 8, EntityAttributeModifier.Operation.ADDITION));
        // If the player has access to ring slots, this will give them an extra one
        SlotAttributes.addSlotModifier(modifiers, "hand/ring", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        return modifiers;
    }
}
