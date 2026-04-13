package cc.unilock.dragonfixes.mixin.late.chromaticraft.accessor;

import Reika.ChromatiCraft.Base.CrystalBlock;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = CrystalBlock.class, remap = false)
public interface CrystalBlockAccessor {
    @Invoker
    float callGetSlugPower(EntityLivingBase e);
}
