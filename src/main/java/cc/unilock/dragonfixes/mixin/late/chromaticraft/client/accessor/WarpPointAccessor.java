package cc.unilock.dragonfixes.mixin.late.chromaticraft.client.accessor;

import Reika.ChromatiCraft.Auxiliary.Ability.WarpPoint;
import Reika.DragonAPI.Instantiable.Data.Immutable.WorldLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = WarpPoint.class, remap = false)
public interface WarpPointAccessor {
    @Invoker("<init>")
    static WarpPoint create(String s, WorldLocation loc) {
        throw new IllegalStateException("WarpPointAccessor#create invoked!?");
    }
}
