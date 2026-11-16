package cc.unilock.dragonfixes.mixin.late.voidmonster;

import Reika.VoidMonster.Entity.EntityVoidMonster;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityVoidMonster.class, remap = false)
public abstract class EntityVoidMonsterMixin extends Entity {
    public EntityVoidMonsterMixin() {
        super(null);
        throw new IllegalStateException("EntityVoidMonsterMixin instantiated!?");
    }

    @WrapMethod(method = "isNetherVoid")
    private boolean isNetherVoid(Operation<Boolean> original) {
        return original.call() && this.posY > 127;
    }
}
