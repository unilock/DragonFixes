package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.World.Dimension.ChromaDimensionManager;
import Reika.DragonAPI.DragonAPICore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(value = ChromaDimensionManager.class, remap = false)
public class ChromaDimensionManagerMixin {
    /**
     * @author unilock
     * @reason Xaero's maps support
     */
    @Inject(method = "resetDimensionClient", at = @At("TAIL"))
    private static void resetDimensionClient(CallbackInfo ci) {
        File xaeroWorldMap = new File(DragonAPICore.getMinecraftDirectory(), "XaeroWorldMap");
        if (xaeroWorldMap.exists() && xaeroWorldMap.isDirectory()) {
            for (File save : xaeroWorldMap.listFiles()) {
                File dim = new File(save, "DIM60");
                if (dim.exists()) {
                    dim.delete();
                }
            }
        }
    }
}
