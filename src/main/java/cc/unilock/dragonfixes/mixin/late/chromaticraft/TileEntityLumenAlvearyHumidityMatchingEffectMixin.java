package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "Reika/ChromatiCraft/ModInterface/Bees/TileEntityLumenAlveary$HumidityMatchingEffect", remap = false)
public class TileEntityLumenAlvearyHumidityMatchingEffectMixin {
    /**
     * @author thegamemaster1234
     * @reason return true
     */
    @Overwrite
    protected boolean worksWhenBeesDoNot() {
        return true;
    }
}
