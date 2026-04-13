package cc.unilock.dragonfixes.mixin.late.chromaticraft.accessor;

import Reika.ChromatiCraft.TileEntity.Networking.TileEntityCrystalBroadcaster;
import Reika.DragonAPI.Instantiable.Data.Immutable.WorldLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = TileEntityCrystalBroadcaster.class, remap = false)
public interface TileEntityCrystalBroadcasterAccessor {
    @Accessor
    void setInterference(WorldLocation value);
}
