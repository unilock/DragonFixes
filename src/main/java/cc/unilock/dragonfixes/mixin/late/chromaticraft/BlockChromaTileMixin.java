package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.BlockChromaTile;
import cc.unilock.dragonfixes.DragonFixesConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraftforge.fluids.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BlockChromaTile.class, remap = false)
public class BlockChromaTileMixin {
    @Definition(id = "IFluidHandler", type = IFluidHandler.class)
    @Expression("? instanceof IFluidHandler")
    @ModifyExpressionValue(method = "getWailaBody", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean getWailaBody$IFluidHandler(boolean original) {
        return !DragonFixesConfig.disableWailaPlugins;
    }
}
