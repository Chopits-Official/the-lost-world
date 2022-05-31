package com.chopits.tlw.attributes;

import com.chopits.tlw.mixin.EntityAttributesInvoker;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;

public final class Attributes {
    public static final EntityAttribute GENERIC_MAX_MAGIC = EntityAttributesInvoker.callRegister("generic.max_magic", new ClampedEntityAttribute("attribute.name.generic.max_magic", 16.0F, 0.0F, 1024.0F).setTracked(true));
    public static final EntityAttribute GENERIC_MAX_TALENT_LEVEL = EntityAttributesInvoker.callRegister("generic.max_talent_level", new ClampedEntityAttribute("attribute.name.generic.max_talent_level", 32.0F, 0.0F, 32.0F).setTracked(true));
    public static final EntityAttribute POINT_MAX_STR = EntityAttributesInvoker.callRegister("point.max_str", new ClampedEntityAttribute("attribute.name.point.max_str", 16.0F, 0.0F, 16.0F).setTracked(true));//strength
    public static final EntityAttribute POINT_MAX_DEX = EntityAttributesInvoker.callRegister("point.max_dex", new ClampedEntityAttribute("attribute.name.point.max_dex", 16.0F, 0.0F, 16.0F).setTracked(true));//dexterity
    public static final EntityAttribute POINT_MAX_MYS = EntityAttributesInvoker.callRegister("point.max_mys", new ClampedEntityAttribute("attribute.name.point.max_mys", 16.0F, 0.0F, 16.0F).setTracked(true));//mysterious
    public static final EntityAttribute POINT_MAX_CON = EntityAttributesInvoker.callRegister("point.max_con", new ClampedEntityAttribute("attribute.name.point.max_con", 16.0F, 0.0F, 16.0F).setTracked(true));//constitution
    public static final EntityAttribute POINT_MAX_BEL = EntityAttributesInvoker.callRegister("point.max_bel", new ClampedEntityAttribute("attribute.name.point.max_bel", 16.0F, 0.0F, 16.0F).setTracked(true));//belief
}
