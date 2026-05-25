package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.Render.ISBRH.GlowTreeRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = GlowTreeRenderer.class, remap = false)
public class GlowTreeRendererMixin {
    @ModifyExpressionValue(method = "renderWorldBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I"))
    private int renderWorldBlock(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }
}
