package cc.unilock.chromatifixes.mixin.late.reactorcraft;

import Reika.ReactorCraft.TileEntities.Processing.TileEntityCentrifuge;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityCentrifuge.class, remap = false)
public class TileEntityCentrifugeMixin {

    /**
     * @author thegamemaster1234
     * @reason fix isotope centrifuge allowing items to exit from the top/bottom instead of the sides
     */
    @Overwrite
    public boolean canItemExitToSide(ForgeDirection dir) {
        return (dir.offsetY == 0);
    }
}
