package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Magic.Network.CrystalNetworker;
import Reika.ChromatiCraft.TileEntity.Networking.TileEntityCrystalBroadcaster;
import Reika.ChromatiCraft.TileEntity.Networking.TileEntityCrystalRepeater;
import Reika.DragonAPI.Instantiable.Data.Immutable.WorldLocation;
import cc.unilock.dragonfixes.mixin.late.chromaticraft.accessor.TileEntityCrystalBroadcasterAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityCrystalBroadcaster.class, remap = false)
public abstract class TileEntityCrystalBroadcasterMixin extends TileEntityCrystalRepeater {
    @Shadow
    private WorldLocation interference;

    /**
     * @author thegamemaster1234
     * @reason check thisWorldLocation != thatWorldLocation to prevent self-interference (MixinExtras 0.5.0 will be able to do this without an @Overwrite)
     */
    @Overwrite
    private void checkInterfere() {
        TileEntityCrystalBroadcaster te = CrystalNetworker.instance.getNearestTileOfType(this, TileEntityCrystalBroadcaster.class, 384.0F);
        if (te != null) {
            WorldLocation thisWorldLocation = new WorldLocation(this);
            WorldLocation thatWorldLocation = new WorldLocation(te);

            // the change
            if (!thisWorldLocation.equals(thatWorldLocation)) {
                this.interference = thatWorldLocation;
                ((TileEntityCrystalBroadcasterAccessor) te).setInterference(thisWorldLocation);
            }

            return;
        }

        this.interference = null;
    }
}
