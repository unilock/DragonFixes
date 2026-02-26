package cc.unilock.chromatifixes.mixin.late.reactorcraft;

import Reika.ChromatiCraft.API.Interfaces.WorldRift;
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper;
import Reika.ReactorCraft.Auxiliary.ReactorTyped;
import Reika.ReactorCraft.Base.TileEntityNuclearBoiler;
import Reika.ReactorCraft.Registry.ReactorType;
import Reika.ReactorCraft.TileEntities.TileEntityHeatPipe;
import Reika.RotaryCraft.Auxiliary.Interfaces.HeatConduction;
import cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor.TileEntityHeatPipeAccessor;
import cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor.TileEntityNuclearBoilerAccessor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileEntityHeatPipe.class, remap = false)
public class TileEntityHeatPipeMixin {

    /**
     * @author thegamemaster1234
     * @reason enable heat pipe passive heat loss and temperature visuals
     */
    @Inject(method="updateEntity", at=@At("TAIL"))
    public void updateEntity(World world, int x, int y, int z, int meta, CallbackInfo ci) {
        TileEntityHeatPipe thisTE = (TileEntityHeatPipe)(Object)this;
        TileEntityHeatPipeAccessor thisTEAcc = (TileEntityHeatPipeAccessor)this;

        if(!world.isRemote && thisTE.getTicksExisted() % 32 == 0) {
            thisTEAcc.ventHeatAcc(world, x, y, z);
        }
    }

    /**
     * @author thegamemaster1234
     * @reason stop heat pipes from losing all energy on world load
     * <br> only set heat pipes to ambient on first tick if energy value is 0
     */
    @Overwrite
    protected void onFirstTick(World world, int x, int y, int z) {
        TileEntityHeatPipeAccessor thisTEAcc = (TileEntityHeatPipeAccessor)this;
        if (thisTEAcc.getHeatEnergy() == 0) {
            thisTEAcc.setHeatEnergy(ReikaWorldHelper.getAmbientTemperatureAt(world, x, y, z) * 3010.8D);
        }
    }

    /**
     * @author thegamemaster1234
     * @reason stop heat pipes from setting negative reactor typing or deleting heat for no reason
     * <br> replacement code based on <a href="https://github.com/ReikaKalseki/ReactorCraft/blob/d788f1bcd33c66758d687faaf692c2668595021b/TileEntities/TileEntityHeatPipe.java#L107">original function by Reika</a>
     */
    @Overwrite
    private void balanceHeat(World world, int x, int y, int z) {
        TileEntityHeatPipe thisTE = (TileEntityHeatPipe)(Object)this;
        TileEntityHeatPipeAccessor thisTEAcc = (TileEntityHeatPipeAccessor)this;

        // Check all sides of the block
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity otherTE = thisTE.getAdjacentTileEntity(dir);
            TileEntityHeatPipe pipe = null;

            // Do nothing if no TE found
            if (otherTE == null) continue;

            // Check for other heat pipes, with World Rift support
            if (otherTE instanceof TileEntityHeatPipe tePipe) {
                pipe = tePipe;
            } else if (otherTE instanceof WorldRift rift
                && rift.getTileEntityFrom(dir) instanceof TileEntityHeatPipe tePipe) {
                pipe = tePipe;
            }

            if (pipe != null) {
                thisTEAcc.balanceWithAcc(pipe);
            } else if (thisTEAcc.canConnectToMachineAcc(otherTE.getBlockType(), otherTE.getBlockMetadata(), dir, otherTE)) {
                HeatConduction conductor = (HeatConduction)otherTE;
                double otherHeatEnergy = TileEntityHeatPipe.getNetHeat(conductor);
                double myHeatEnergy = thisTE.getNetHeatEnergy();
                double otherTemp = TileEntityHeatPipe.getNetTemperature(conductor);
                double myTemp = TileEntityHeatPipe.getTemperatureForPipe(thisTE, true);

                // Allow heat intake if the other TE has both more energy and more temperature
                boolean intake = otherHeatEnergy > myHeatEnergy && otherTemp > myTemp && conductor.allowHeatExtraction();
                // Allow heat export if the other TE has less temperature
                boolean outtake = myTemp > otherTemp && conductor.allowExternalHeating();
                // Heat will not move if the other TE has more temperature but less energy

                if (intake || outtake) {
                    // Energy difference between sources
                    // Will be >0 if sending heat, <0 if receiving heat
                    double transferRate = 0.25;
                    double energyDiff = (myHeatEnergy - otherHeatEnergy) * transferRate;

                    // MODIFIED: Amount of temperature units to put back in the conductor
                    int put = TileEntityHeatPipe.getTemperatureForHeat(otherHeatEnergy + energyDiff, conductor);

                    // NEW: Energy lost when modifying heat due to integer truncation
                    // If difference >0, this represents the amount of energy unable to be sent
                    // If difference <0, this represents the extra energy taken while balancing
                    // It might sound like the math needs to be different for each case, but it doesn't!
                    double remainder = ((otherHeatEnergy + energyDiff) / conductor.heatEnergyPerDegree()) % 1.0D;
                    remainder *= conductor.heatEnergyPerDegree();

                    // Set the conductor's heat level to recombined total
                    conductor.setTemperature(put + conductor.getAmbientTemperature());

                    // Adjust heat energy according to the amount given or received
                    // NEW: Recycle temperature units lost to integer truncation
                    thisTEAcc.setHeatEnergy(thisTEAcc.getHeatEnergy() - energyDiff + remainder);

                    // Reactor energy typing handling
                    if (intake) {
                        ReactorType type = null;
                        if (otherTE instanceof ReactorTyped typed) {
                            type = typed.getReactorType();
                        }
                        // If conductor has reactor types, gain an amount of those types based on energy received
                        // MODIFIED: Use heat units instead of energy to prevent uncharacteristically large values
                        if (type != null) {
                            thisTEAcc.getReactorTypes().addValue(
                                type,
                                TileEntityHeatPipe.getTemperatureForHeat(energyDiff, conductor)
                            );
                        }
                    } else {
                        // NOTE: This code can now run if energyDiff==0. Unknown if that could do anything weird
                        // If sending to a boiler, apply the heat types gained
                        if (otherTE instanceof TileEntityNuclearBoiler) {
                            // This accessor exists to avoid having to build with Buildcraft :aaaaaa:
                            ((TileEntityNuclearBoilerAccessor)otherTE).setReactorTypesAcc(thisTEAcc.getReactorTypes());
                        }
                    }
                }
            }
        }
    }
}
