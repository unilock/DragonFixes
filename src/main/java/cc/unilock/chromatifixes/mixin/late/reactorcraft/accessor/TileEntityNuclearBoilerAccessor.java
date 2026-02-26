package cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor;

import Reika.DragonAPI.Instantiable.Data.Proportionality;
import Reika.ReactorCraft.Base.TileEntityNuclearBoiler;
import Reika.ReactorCraft.Registry.ReactorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = TileEntityNuclearBoiler.class, remap = false)
public interface TileEntityNuclearBoilerAccessor {
    @Invoker("setReactorTypes")
    void setReactorTypesAcc(Proportionality<ReactorType> p);
}
