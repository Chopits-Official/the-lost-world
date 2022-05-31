package com.chopits.tlw.mixin;

import com.chopits.tlw.TheLostWorld;
import com.chopits.tlw.attributes.Attributes;
import com.chopits.tlw.entity.player.IPlayerEntity;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IPlayerEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "<init>",
            at = @At(value = "RETURN")
    )
    private void injectInit(World world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {
        TheLostWorld.LOGGER.info("Init Object");//TODO
        this.setTalentLevel(16);//TODO
        this.setMagic(16.0F);
        this.setMys(10);//TODO
        this.setStr(1);
        this.setDex(1);
        this.setCon(1);
        this.setBel(1);

    }

    @Inject(
            method = "createPlayerAttributes",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void injectCreatePlayerAttributes(@NotNull CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(
                LivingEntity.createLivingAttributes()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f)
                        .add(EntityAttributes.GENERIC_ATTACK_SPEED)
                        .add(EntityAttributes.GENERIC_LUCK)
                        .add(Attributes.POINT_MAX_STR)
                        .add(Attributes.POINT_MAX_MYS)
                        .add(Attributes.POINT_MAX_DEX)
                        .add(Attributes.POINT_MAX_CON)
                        .add(Attributes.POINT_MAX_BEL)
                        .add(Attributes.GENERIC_MAX_TALENT_LEVEL)
                        .add(Attributes.GENERIC_MAX_MAGIC)
        );
    }

    @Inject(
            method = "readCustomDataFromNbt",
            at = @At(value = "RETURN")
    )
    private void injectReadCustomDataFromNbt(@NotNull NbtCompound nbt, CallbackInfo ci) {
        TheLostWorld.LOGGER.info("Start read NBT tags");//TODO
        if (nbt.contains("TalentLevel")) this.setTalentLevel(nbt.getInt("TalentLevel"));
        if (nbt.contains("STR") && nbt.contains("DEX") && nbt.contains("MYS") && nbt.contains("CON") && nbt.contains("BEL")) {
            this.setStr(nbt.getInt("STR"));
            this.setDex(nbt.getInt("DEX"));
            this.setMys(nbt.getInt("MYS"));
            this.setBel(nbt.getInt("BEL"));
            this.setCon(nbt.getInt("CON"));
        }
        if (nbt.contains("Magic")) this.setMagic(nbt.getFloat("Magic"));
        TheLostWorld.LOGGER.info("Print Attributes :");
        TheLostWorld.LOGGER.info("this.getTalentLevel() = " + this.getTalentLevel() + "; this.getMaxTalentLevel() = " + this.getMaxTalentLevel());
        TheLostWorld.LOGGER.info("this.getMys() = " + this.getMys() + "; this.getMaxMys() = " + this.getMaxMys());
        TheLostWorld.LOGGER.info("this.getMagic() = " + this.getMagic() + "; this.getMaxMagic() = " + this.getMaxMagic());
    }

    @Inject(
            method = "writeCustomDataToNbt",
            at = @At(value = "RETURN")
    )
    private void injectWriteCustomDataToNbt(@NotNull NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("TalentLevel", this.getTalentLevel());
        nbt.putInt("STR", this.getStr());
        nbt.putInt("DEX", this.getDex());
        nbt.putInt("MYS", this.getMys());
        nbt.putInt("CON", this.getCon());
        nbt.putInt("BEL", this.getBel());
        nbt.putFloat("Magic", this.getMagic());
    }

    @Inject(
            method = "damage",
            at = @At(value = "HEAD")
    )
    private void injectDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

    }

    @Inject(
            method = "initDataTracker",
            at = @At(value = "RETURN")
    )
    private void injectInitDataTracker(CallbackInfo ci) {
        TheLostWorld.LOGGER.info("Init Data Tracker");//TODO
        this.dataTracker.startTracking(TheLostWorld.TALENT_LEVEL, 0);
        this.dataTracker.startTracking(TheLostWorld.MAGIC, 0.0F);
        this.dataTracker.startTracking(TheLostWorld.STR, 0);
        this.dataTracker.startTracking(TheLostWorld.MYS, 0);
        this.dataTracker.startTracking(TheLostWorld.DEX, 0);
        this.dataTracker.startTracking(TheLostWorld.CON, 0);
        this.dataTracker.startTracking(TheLostWorld.BEL, 0);
    }

    //Custom Attributes
    @Override
    public float getMaxMagic() {
        return (float) Objects.requireNonNull(this.getAttributeInstance(Attributes.GENERIC_MAX_MAGIC)).getValue();
    }

    @Override
    public float getMagic() {
        return this.dataTracker.get(TheLostWorld.MAGIC);
    }

    @Override
    public void setMagic(float magic) {
        this.dataTracker.set(TheLostWorld.MAGIC, MathHelper.clamp(magic, 0.0F, this.getMaxMagic()));
    }

    @Override
    public int getMaxTalentLevel() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.GENERIC_MAX_TALENT_LEVEL)).getValue();
    }

    @Override
    public int getTalentLevel() {
        return this.dataTracker.get(TheLostWorld.TALENT_LEVEL);
    }

    @Override
    public void setTalentLevel(int talentLevel) {
        this.dataTracker.set(TheLostWorld.TALENT_LEVEL, MathHelper.clamp(talentLevel, 0, this.getMaxTalentLevel()));
    }

    @Override
    public int getMaxStr() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.POINT_MAX_STR)).getValue();
    }

    @Override
    public int getStr() {
        return this.dataTracker.get(TheLostWorld.STR);
    }

    @Override
    public void setStr(int str) {
        if (!this.talentAccessible(str)) return;
        this.dataTracker.set(TheLostWorld.STR, MathHelper.clamp(str, 0, this.getMaxStr()));
    }

    @Override
    public int getMaxMys() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.POINT_MAX_MYS)).getValue();
    }

    @Override
    public int getMys() {
        return this.dataTracker.get(TheLostWorld.MYS);
    }

    @Override
    public void setMys(int mys) {
        if (!this.talentAccessible(mys)) return;
        this.dataTracker.set(TheLostWorld.MYS, MathHelper.clamp(mys, 0, this.getMaxMys()));
        Objects.requireNonNull(this.getAttributeInstance(Attributes.GENERIC_MAX_MAGIC)).setBaseValue(this.getMys() * 16.0F);
        this.setMagic(this.getMys() * 16);
    }

    @Override
    public int getMaxDex() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.POINT_MAX_DEX)).getValue();
    }

    @Override
    public int getDex() {
        return this.dataTracker.get(TheLostWorld.DEX);
    }

    @Override
    public void setDex(int dex) {
        if (!this.talentAccessible(dex)) return;
        this.dataTracker.set(TheLostWorld.DEX, MathHelper.clamp(dex, 0, this.getMaxDex()));
    }

    @Override
    public int getMaxCon() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.POINT_MAX_CON)).getValue();
    }

    @Override
    public int getCon() {
        return this.dataTracker.get(TheLostWorld.CON);
    }

    @Override
    public void setCon(int con) {
        if (!this.talentAccessible(con)) return;
        double h = Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).getBaseValue();
        int i = this.getCon() * 2;
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue( h - i);
        this.dataTracker.set(TheLostWorld.CON, MathHelper.clamp(con, 0, this.getMaxCon()));
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue( h - i + (this.getCon() * 2));
    }

    @Override
    public int getMaxBel() {
        return (int) Objects.requireNonNull(this.getAttributeInstance(Attributes.POINT_MAX_BEL)).getValue();
    }

    @Override
    public int getBel() {
        return this.dataTracker.get(TheLostWorld.BEL);
    }

    @Override
    public void setBel(int bel) {
        if (!this.talentAccessible(bel)) return;
        this.dataTracker.set(TheLostWorld.BEL, MathHelper.clamp(bel, 0, this.getMaxBel()));
    }

    @Override
    public boolean talentAccessible(int i) {
        return this.getStr() + this.getMys() + this.getDex() + this.getCon() + this.getBel() + i <= this.getTalentLevel();
    }
}
