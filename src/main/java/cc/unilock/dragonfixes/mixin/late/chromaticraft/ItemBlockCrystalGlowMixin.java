package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Items.ItemBlock.ItemBlockCrystalGlow;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemBlockCrystalGlow.class, remap = false)
public class ItemBlockCrystalGlowMixin {
    @Expression("? / 16")
    @ModifyExpressionValue(method = "getBase", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static int getBase(int original) {
        if (original > 5) {
            return original % 6;
        }
        return original;
    }
}
