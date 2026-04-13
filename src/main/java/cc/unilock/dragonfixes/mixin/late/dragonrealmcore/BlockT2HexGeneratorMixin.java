package cc.unilock.dragonfixes.mixin.late.dragonrealmcore;

import Reika.ChromatiCraft.Base.TileEntity.TileEntityAdjacencyUpgrade;
import Reika.ChromatiCraft.Registry.CrystalElement;
import Reika.DragonRealmCore.ItemsAndBlocks.BlockT2HexGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlockT2HexGenerator.class, remap = false)
public class BlockT2HexGeneratorMixin {
    @Shadow
    private static TileEntityAdjacencyUpgrade.AdjacencyCheckHandlerImpl adjacency;

    /**
     * @author thegamemaster1234, unilock
     * @reason make sure adjacency is never null (the field is static, but the methods referencing it are not!)
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        adjacency = TileEntityAdjacencyUpgrade.getOrCreateAdjacencyCheckHandler(CrystalElement.YELLOW, null);
    }
}
