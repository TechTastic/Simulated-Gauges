package io.github.techtastic.simulated_gauges.registry;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.gui.AllIcons;
import io.github.techtastic.simulated_gauges.SimulatedGauges;
import net.createmod.catnip.theme.Color;
import net.liukrast.deployer.lib.mixin.accessors.AllIconsAccessor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class Icons extends AllIcons {
    private static final ResourceLocation ICON_ATLAS = ResourceLocation.fromNamespaceAndPath(SimulatedGauges.MOD_ID, "textures/gui/icons.png");
    private static final int ICON_ATLAS_SIZE = 64;

    public static final Icons X = new Icons(0, 0);
    public static final Icons Y = new Icons(1, 0);
    public static final Icons Z = new Icons(2, 0);
    public static final Icons W = new Icons(3, 0);
    public static final Icons METERS = new Icons(0, 1);
    public static final Icons PASCALS = new Icons(1, 1);

    public Icons(int x, int y) {
        super(x, y);
    }

    @Override
    public void bind() {
        RenderSystem.setShaderTexture(0, ICON_ATLAS);
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y) {
        graphics.blit(
                ICON_ATLAS, x, y, 0,
                ((AllIconsAccessor) this).getIconX(),
                ((AllIconsAccessor) this).getIconY(),
                16, 16, ICON_ATLAS_SIZE, ICON_ATLAS_SIZE
        );
    }

    @Override
    public void render(PoseStack ms, MultiBufferSource buffer, int color) {
        var builder = buffer.getBuffer(RenderType.text(ICON_ATLAS));
        var matrix = ms.last().pose();
        var rgb = new Color(color);
        var light = LightTexture.FULL_BRIGHT;

        var vec1 = new Vec3(0.0, 0.0, 0.0);
        var vec2 = new Vec3(0.0, 1.0, 0.0);
        var vec3 = new Vec3(1.0, 1.0, 0.0);
        var vec4 = new Vec3(1.0, 0.0, 0.0);
        var iconX = ((AllIconsAccessor) this).getIconX();
        var iconY = ((AllIconsAccessor) this).getIconY();

        var u1 = iconX * 1f / ICON_ATLAS_SIZE;
        var u2 = (iconX + 16) * 1f / ICON_ATLAS_SIZE;
        var v1 = iconY * 1f / ICON_ATLAS_SIZE;
        var v2 = (iconY + 16) * 1f / ICON_ATLAS_SIZE;

        var cast = (AllIconsAccessor) this;

        cast.invokeVertex(builder, matrix, vec1, rgb, u1, v1, light);
        cast.invokeVertex(builder, matrix, vec2, rgb, u1, v2, light);
        cast.invokeVertex(builder, matrix, vec3, rgb, u2, v2, light);
        cast.invokeVertex(builder, matrix, vec4, rgb, u2, v1, light);
    }
}
