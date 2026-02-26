package cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor;

import Reika.DragonAPI.Instantiable.Data.Proportionality;
import Reika.ReactorCraft.Registry.ReactorType;
import Reika.ReactorCraft.TileEntities.TileEntityHeatPipe;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = TileEntityHeatPipe.class, remap = false)
public interface TileEntityHeatPipeAccessor {
    @Accessor
    double getHeatEnergy();

    @Accessor
    void setHeatEnergy(double value);

    @Accessor
    Proportionality<ReactorType> getReactorTypes();

    @Invoker("balanceWith")
    void balanceWithAcc(TileEntityHeatPipe other);

    @Invoker("canConnectToMachine")
    boolean canConnectToMachineAcc(Block id, int meta, ForgeDirection dir, TileEntity te);

    @Invoker("ventHeat")
    void ventHeatAcc(World world, int x, int y, int z);
}
