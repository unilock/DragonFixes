package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.CrystalTypeBlock;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CrystalTypeBlock.class, remap = false)
public class CrystalTypeBlockMixin {
    @Inject(method = "breakBlock", at = @At("HEAD"), remap = true)
    private void breakBlock(CallbackInfo ci, @Local(argsOnly = true, ordinal = 3) LocalIntRef metaRef) {
        if (metaRef.get() > 15) {
            metaRef.set(metaRef.get() % 16);
        }
    }

    @ModifyExpressionValue(method = "ding(Lnet/minecraft/world/World;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockMetadata(III)I", remap = true))
    private static int ding(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }

    @ModifyExpressionValue(method = "getLightValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I", remap = true), remap = true)
    private int getLightValue(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }

    @ModifyExpressionValue(method = "getCrystalElement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I", remap = true))
    private int getCrystalElement(int original) {
        if (original > 15) {
            return original % 16;
        }
        return original;
    }
}
