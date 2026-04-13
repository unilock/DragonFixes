package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Registry.CrystalElement;
import Reika.ChromatiCraft.World.IWG.PylonGenerator;
import cc.unilock.dragonfixes.DragonFixes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.EnumMap;

@Mixin(value = PylonGenerator.class, remap = false)
public class PylonGeneratorMixin {
    @Shadow
    @Final
    private EnumMap<CrystalElement, Collection<PylonGenerator.PylonEntry>> colorCache;

    /**
     * @author thegamemaster1234
     * @reason catch ConcurrentModificationException and retry if necessary
     */
    @Overwrite
    public PylonGenerator.PylonEntry getNearestPylonSpawn(World world, double x, double y, double z, CrystalElement e) {
        Collection<PylonGenerator.PylonEntry> c = this.colorCache.get(e);
        if (c == null) {
            return null;
        } else {
            double dist = Double.POSITIVE_INFINITY;
            PylonGenerator.PylonEntry close = null;

            boolean repeat;
            do {
                repeat = false;

                try {
                    for (PylonGenerator.PylonEntry loc : c) {
                        if (loc.location.dimensionID == world.provider.dimensionId) {
                            double d = loc.location.getDistanceTo(x, y, z);
                            if (d < dist) {
                                dist = d;
                                close = loc;
                            }
                        }
                    }
                } catch (ConcurrentModificationException ignored) {
                    DragonFixes.LOGGER.warn("ConcurrentModificationException caught in PylonGenerator#getNearestPylonSpawn");
                    repeat = true;
                }
            } while (repeat);


            return close;
        }
    }
}
