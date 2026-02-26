package cc.unilock.chromatifixes.mixin.late.reactorcraft;

import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell;
import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell.LiquidStates;
import cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor.TileEntityReactorBaseAccessor;
import cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor.TileEntityWaterCellAccessor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityWaterCell.class, remap = false)
public class TileEntityWaterCellMixin {

    /**
     * @author thegamemaster1234
     * @reason fix coolant cells voiding fluids, based on <a href="https://github.com/ReikaKalseki/ReactorCraft/blob/d788f1bcd33c66758d687faaf692c2668595021b/TileEntities/Fission/TileEntityWaterCell.java#L44">original method by Reika</a>
     */
    @Overwrite
    public void updateEntity(World world, int x, int y, int z, int meta) {
        TileEntityWaterCell thisTE = (TileEntityWaterCell)(Object)this;
        TileEntityWaterCellAccessor thisTEAcc = (TileEntityWaterCellAccessor)this;
        TileEntityReactorBaseAccessor thisTEBaseAcc = (TileEntityReactorBaseAccessor)this;

        // Move fluid downwards to other coolant cells if possible
        TileEntity downTE = thisTE.getAdjacentTileEntity(ForgeDirection.DOWN);
        if (downTE instanceof TileEntityWaterCell cellTE) {
            if (cellTE.getLiquidState() == LiquidStates.EMPTY && thisTE.getLiquidState() != LiquidStates.EMPTY) {
                cellTE.setLiquidState(thisTE.getLiquidState());
                thisTE.setLiquidState(LiquidStates.EMPTY);
            }
        }

        // Take fluid from above Forge fluid handlers if possible
        // MODIFIED: Removed explicit reservoir compatibility (unnecessary)
        // MODIFIED: Now properly checks if the fluid is acceptable before draining it
        if (thisTE.getLiquidState() == LiquidStates.EMPTY) {
            TileEntity upTE = thisTE.getAdjacentTileEntity(ForgeDirection.UP);

            if (upTE instanceof IFluidHandler handler) {
                FluidStack liq = handler.drain(ForgeDirection.DOWN, FluidContainerRegistry.BUCKET_VOLUME, false);
                if (liq != null) {
                    LiquidStates liqState = LiquidStates.getState(liq.getFluid());
                    if (liq.amount >= FluidContainerRegistry.BUCKET_VOLUME
                        && liqState != null
                        && liqState != LiquidStates.EMPTY) {
                        handler.drain(ForgeDirection.DOWN, FluidContainerRegistry.BUCKET_VOLUME, true);
                        thisTE.setLiquidState(liqState);
                    }
                }
            }
        }

        // Update temperature
        thisTEBaseAcc.getThermalTicker().update();
        if (thisTEBaseAcc.getThermalTicker().checkCap() && !world.isRemote) {
            thisTEAcc.updateTemperatureAcc(world, x, y, z);
        }
    }
}
