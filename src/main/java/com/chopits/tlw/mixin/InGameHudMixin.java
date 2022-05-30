package com.chopits.tlw.mixin;

import com.chopits.tlw.TheLostWorld;
import com.chopits.tlw.attributes.Attributes;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V")
    )
    private void injectRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

    }

    /*
    @Override
    public void renderMagicBar(MatrixStack matrices) {
        assert this.client.player != null;
        int level = (int) Objects.requireNonNull(this.client.player.getAttributeInstance(Attributes.POINT_MAX_MYS)).getValue();
        int magic = (int) Objects.requireNonNull(this.client.player.getAttributeInstance(Attributes.GENERIC_MAX_MAGIC)).getValue();
        this.client.getProfiler().push("magicBar");
        RenderSystem.setShaderTexture(0, TheLostWorld.OVERLAY_TEXTURE);
        int rest = magic % 16;

        if (level > 0) {

        }
    }*/
}
