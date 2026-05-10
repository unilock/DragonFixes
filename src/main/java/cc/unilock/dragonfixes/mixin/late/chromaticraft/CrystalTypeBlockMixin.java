package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.CrystalTypeBlock;
import cc.unilock.dragonfixes.DragonFixes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CrystalTypeBlock.class, remap = false)
public class CrystalTypeBlockMixin {
    @ModifyExpressionValue(method = "ding(Lnet/minecraft/world/World;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockMetadata(III)I"))
    private static int ding(int original) {
        if (original > 15) {
            DragonFixes.LOGGER.debug("Attempted to ding CrystalTypeBlock with metadata > 15");
            return original % 16;
        }
        return original;
    }
}
