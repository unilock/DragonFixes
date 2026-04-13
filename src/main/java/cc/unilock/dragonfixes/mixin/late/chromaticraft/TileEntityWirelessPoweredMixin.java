package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.TileEntity.TileEntityAdjacencyUpgrade;
import Reika.ChromatiCraft.Base.TileEntity.TileEntityWirelessPowered;
import Reika.ChromatiCraft.Registry.CrystalElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityWirelessPowered.class, remap = false)
public class TileEntityWirelessPoweredMixin {
    @Shadow
    private static TileEntityAdjacencyUpgrade.AdjacencyCheckHandlerImpl adjacency;

    /**
     * @author thegamemaster1234, unilock
     * @reason make sure adjacency is never null, @Overwrite to fail hard
     */
    @Overwrite
    public static void loadAdjacencyHandler() {
        adjacency = TileEntityAdjacencyUpgrade.getOrCreateAdjacencyCheckHandler(CrystalElement.BLACK, null);
    }
}
