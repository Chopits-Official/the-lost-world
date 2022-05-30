package com.chopits.tlw.mixin;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityAttributes.class)
public interface EntityAttributesInvoker {
    @Invoker("register")
    static EntityAttribute callRegister(String id, EntityAttribute attribute) {
        throw new AssertionError();
    }
}
