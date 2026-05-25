package cc.unilock.dragonfixes.mixin.late.voidmonster;

import Reika.VoidMonster.World.AmbientSoundGenerator;
import cc.unilock.dragonfixes.DragonFixesConfig;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = AmbientSoundGenerator.class, remap = false)
public class AmbientSoundGeneratorMixin {
    @WrapMethod(method = "isHardcodedAllowed")
    private boolean isHardcodedAllowed(int id, Operation<Boolean> original) {
        return !DragonFixesConfig.disableVoidMonsterHardcode && original.call(id);
    }
}
