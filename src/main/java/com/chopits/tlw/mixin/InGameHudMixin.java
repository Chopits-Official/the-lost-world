package com.chopits.tlw.mixin;

import com.chopits.tlw.TheLostWorld;
import com.chopits.tlw.client.hud.IInGameHud;
import com.chopits.tlw.entity.player.IPlayerEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper implements IInGameHud {
    @Shadow @Final private MinecraftClient client;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/util/math/MatrixStack;I)V")
    )
    private void injectRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        this.renderMagicBar(matrices);
    }


    @Override
    public void renderMagicBar(MatrixStack matrices) {
        assert this.client.player != null;
        int magic = Math.round( ( ( IPlayerEntity ) this.client.player ).getMagic() );
        int maxMagic = Math.round(((IPlayerEntity) this.client.player).getMaxMagic() );

        int magicLv = magic / 16;
        int magicRs = magic % 16;

        int maxMagicLv = maxMagic / 16;
        int maxMagicRs = maxMagic % 16;

        RenderSystem.setShaderTexture(0, TheLostWorld.OVERLAY_TEXTURE);
        this.client.getProfiler().push("magicBar");

        //Render the base
        if (maxMagic > 0) {
            //Draw the head of the bar
            int x = this.scaledWidth / 32;
            int y = this.scaledHeight / 24;
            this.drawTexture(matrices, x, y, 0, 0, 1, 5);
            x++;

            if (maxMagicLv > 0) {
                for (int i = 0; i < (maxMagicLv - 1); i++) {
                    this.drawTexture(matrices, x, y, 1, 0, 15, 5);
                    x += 15;
                }

                this.drawTexture(matrices, x, y, 1, 0, 14, 5);
                x += 14;

            }

            if (maxMagicRs > 0) {
                this.drawTexture(matrices, x, y, 15, 0, 1, 5);
                x++;
                int w = maxMagicRs - 2;
                if (w > 0) {
                    this.drawTexture(matrices, x, y, 1, 0, w, 5);
                    x += w;
                }
            }

            this.drawTexture(matrices, x, y, 0, 0, 1, 5);
            }

        //Render the Cover
        if (magic > 0) {
            //Draw the head of the bar
            int x = this.scaledWidth / 32;
            int y = this.scaledHeight / 24;
            this.drawTexture(matrices, x, y, 16, 0, 1, 5);
            x++;

            if (magicLv > 0) {
                for (int i = 0; i < (magicLv - 1); i++) {
                    this.drawTexture(matrices, x, y, 17, 0, 15, 5);
                    x += 15;
                }

                this.drawTexture(matrices, x, y, 17, 0, 14, 5);
                x += 14;

            }

            if (magicRs > 0) {
                this.drawTexture(matrices, x, y, 31, 0, 1, 5);
                x++;
                int w = magicRs - 2;
                if (w > 0) {
                    this.drawTexture(matrices, x, y, 17, 0, w, 5);
                    x += w;
                }
            }

            this.drawTexture(matrices, x, y, 16, 0, 1, 5);
        }
    }
}
