package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Magic.Progression.ProgressStage;
import Reika.ChromatiCraft.Magic.Progression.ProgressionManager;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.HashSet;

@Mixin(value = ProgressionManager.class, remap = false)
public abstract class ProgressionManagerMixin {
    @Shadow
    public abstract Collection<ProgressStage> getStagesFor(EntityPlayer ep);

    /**
     * @author unilock
     * @reason use HashSet instead of ArrayList to have equals ignore order
     */
    // apologies for the unconditional cancel, but @Overwrite kept asking for this method to be transient...?
    @Inject(method = "isProgressionEqual", at = @At("HEAD"), cancellable = true)
    private void isProgressionEqual(EntityPlayer ep1, EntityPlayer ep2, ProgressStage[] ignore, CallbackInfoReturnable<Boolean> cir) {
        // the change
        Collection<ProgressStage> c1 = new HashSet<>(this.getStagesFor(ep1));
        Collection<ProgressStage> c2 = new HashSet<>(this.getStagesFor(ep2));

        for(ProgressStage p : ignore) {
            c1.remove(p);
            c2.remove(p);
        }

        cir.setReturnValue(c1.equals(c2));
    }
}
