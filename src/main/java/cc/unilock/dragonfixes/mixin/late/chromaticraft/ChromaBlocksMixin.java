package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Registry.ChromaBlocks;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ChromaBlocks.class, remap = false)
public class ChromaBlocksMixin {
    @Definition(id = "meta", local = @Local(type = int.class, argsOnly = true))
    @Expression("meta / 16")
    @ModifyExpressionValue(method = "getMultiValuedName", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int getMultiValuedName(int original) {
        if (original > 5) {
            return original % 6;
        }
        return original;
    }
}
