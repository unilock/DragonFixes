package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Block.Worldgen.BlockDecoFlower;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockDecoFlower.class)
public class BlockDecoFlowerMixin {
    @ModifyExpressionValue(method = "setBlockBoundsBasedOnState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I"))
    public int setBlockBoundsBasedOnState(int original) {
        if (original > 7) {
            return original % 7;
        }
        return original;
    }
}
