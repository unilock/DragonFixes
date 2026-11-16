package cc.unilock.dragonfixes.mixin.late.voidmonster;

import Reika.VoidMonster.Entity.EntityVoidMonster;
import Reika.VoidMonster.World.MonsterGenerator;
import cc.unilock.dragonfixes.DragonFixesConfig;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MonsterGenerator.class, remap = false)
public class MonsterGeneratorMixin {
    @WrapOperation(method = "spawn", at = @At(value = "INVOKE", target = "LReika/VoidMonster/Entity/EntityVoidMonster;isNetherVoid()Z"))
    private boolean spawn$isNetherVoid(EntityVoidMonster instance, Operation<Boolean> original) {
        return !DragonFixesConfig.disableVoidMonsterNetherRoof && original.call(instance);
    }

    @WrapMethod(method = "isHardcodedAllowed")
    private boolean isHardcodedAllowed(int id, Operation<Boolean> original) {
        return !DragonFixesConfig.disableVoidMonsterHardcode && original.call(id);
    }
}
