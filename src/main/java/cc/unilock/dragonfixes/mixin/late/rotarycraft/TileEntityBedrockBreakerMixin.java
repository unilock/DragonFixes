package cc.unilock.dragonfixes.mixin.late.rotarycraft;

import Reika.DragonAPI.ModInteract.ItemHandlers.FactorizationHandler;
import Reika.RotaryCraft.TileEntities.Production.TileEntityBedrockBreaker;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityBedrockBreaker.class, remap = false)
public class TileEntityBedrockBreakerMixin {
    @Inject(method = "isBedrock", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlock(III)Lnet/minecraft/block/Block;"), cancellable = true)
    private void isBedrock$factorizationBlastedBedrock(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local Block id) {
        if (id == FactorizationHandler.getInstance().bedrockID2) {
            cir.setReturnValue(true);
        }
    }
}
