package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.Render.ISBRH.CrystalGlowRenderer;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CrystalGlowRenderer.class, remap = false)
public class CrystalGlowRendererMixin {
    @Definition(id = "metadata", local = @Local(type = int.class, argsOnly = true, ordinal = 0))
    @Expression("metadata / 16")
    @ModifyExpressionValue(method = "renderInventoryBlock", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int renderInventoryBlock(int original) {
        if (original > 6) {
            return original % 6;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderWorldBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I", remap = true))
    private int renderWorldBlock(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }
}
