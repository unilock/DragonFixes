package cc.unilock.chromatifixes.mixin.late.reactorcraft.accessor;

import Reika.ReactorCraft.TileEntities.Fission.TileEntityWaterCell;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = TileEntityWaterCell.class, remap = false)
public interface TileEntityWaterCellAccessor {
    @Invoker("updateTemperature")
    void updateTemperatureAcc(World world, int x, int y, int z);
}
