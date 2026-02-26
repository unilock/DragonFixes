package cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor;

import Reika.DragonAPI.Instantiable.StepTimer;
import Reika.ReactorCraft.Base.TileEntityReactorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = TileEntityReactorBase.class, remap = false)
public interface TileEntityReactorBaseAccessor {
    @Accessor
    StepTimer getThermalTicker();
}
