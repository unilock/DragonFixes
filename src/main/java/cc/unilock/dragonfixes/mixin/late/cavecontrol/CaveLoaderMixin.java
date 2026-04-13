package cc.unilock.dragonfixes.mixin.late.cavecontrol;

import Reika.CaveControl.CaveLoader;
import cc.unilock.dragonfixes.DragonFixes;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = CaveLoader.class, remap = false)
public class CaveLoaderMixin {
    /**
     * @author thegamemaster1234
     * @reason catch Exception and never return a null biome
     */
    @WrapMethod(method = "getEffectiveBiome")
    private BiomeGenBase getEffectiveBiome(World world, int x, int z, Operation<BiomeGenBase> original) {
        BiomeGenBase biome = null;

        try {
            biome = original.call(world, x, z);
        } catch (Exception e) {
            DragonFixes.LOGGER.warn("Caught Exception in CaveLoader#getEffectiveBiome");
            DragonFixes.LOGGER.warn(e);
        }

        if (biome == null) {
            DragonFixes.LOGGER.warn("CaveLoader#getEffectiveBiome returned null; falling back to ocean");
            return BiomeGenBase.ocean;
        }

        return biome;
    }
}
