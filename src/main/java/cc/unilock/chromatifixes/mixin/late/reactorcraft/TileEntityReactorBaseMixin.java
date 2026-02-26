package cc.unilock.chromatifixes.mixin.late.reactorcraft;

import Reika.ReactorCraft.Base.TileEntityReactorBase;
import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityReactorBase.class, remap = false)
public class TileEntityReactorBaseMixin {
    /**
     * @author thegamemaster1234
     * @reason fix coolant cells always being treated as heat conductance 0
     * <br> NOTE: this enables using coolant cells as type-agnostic conductors i.e. "exploits" (kinda like control rods))
     */
    @Inject(method="getHeatEfficiency", at=@At("HEAD"), cancellable = true)
    public void getHeatEfficiency(TileEntityReactorBase other, CallbackInfoReturnable<Float> ci) {
        if ((Object)this instanceof TileEntityWaterCell
            || other instanceof TileEntityWaterCell) {
            ci.setReturnValue(1.0f);
        }
    }
}
