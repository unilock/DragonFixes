package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Block.Worldgen.BlockTieredPlant;
import cc.unilock.dragonfixes.DragonFixes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BlockTieredPlant.class)
public class BlockTieredPlantMixin {
    @ModifyExpressionValue(method = "setBlockBoundsBasedOnState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlockMetadata(III)I"))
    private int setBlockBoundsBasedOnState(int original) {
        if (original > 6) {
            DragonFixes.LOGGER.debug("Attempted to set block bounds of BlockTieredPlant with metadata > 6");
            return original % 7;
        }
        return original;
    }
}
