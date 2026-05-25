package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.Render.ISBRH.CrystalGlowRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = CrystalGlowRenderer.class, remap = false)
public class CrystalGlowRendererMixin {
    @ModifyConstant(method = "renderInventoryBlock", constant = @Constant(intValue = 16, ordinal = 0))
    private int renderInventoryBlock(int constant) {
        return 6;
    }

    @ModifyExpressionValue(method = "renderWorldBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I", remap = true))
    private int renderWorldBlock(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }
}
