package cc.unilock.dragonfixes.mixin.late.rotarycraft;

import Reika.DragonAPI.ModInteract.ItemHandlers.FactorizationHandler;
import Reika.RotaryCraft.TileEntities.Production.TileEntityBedrockBreaker;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TileEntityBedrockBreaker.class, remap = false)
public class TileEntityBedrockBreakerMixin {
    @ModifyReturnValue(method = "isBedrock", at = @At("RETURN"))
    private boolean isBedrock$factorizationBlastedBedrock(boolean original, @Local Block id) {
        return original || id == FactorizationHandler.getInstance().bedrockID2;
    }
}
