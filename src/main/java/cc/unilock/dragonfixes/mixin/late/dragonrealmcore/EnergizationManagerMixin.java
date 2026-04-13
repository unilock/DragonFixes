package cc.unilock.dragonfixes.mixin.late.dragonrealmcore;

import Reika.DragonRealmCore.Energizer.EnergizationManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EnergizationManager.class, remap = false)
public class EnergizationManagerMixin {
    /**
     * @author thegamemaster1234
     * @reason return 1.0F
     */
    @Overwrite
    public float getEnergizationLevel(World world, int x, int y, int z) {
        return 1.0F;
    }
}
