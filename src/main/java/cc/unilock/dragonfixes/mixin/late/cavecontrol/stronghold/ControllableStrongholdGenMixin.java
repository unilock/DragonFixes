package cc.unilock.dragonfixes.mixin.late.cavecontrol.stronghold;

import Reika.CaveControl.Generators.ControllableStrongholdGen;
import Reika.CaveControl.Registry.CaveOptions;
import Reika.DragonAPI.DragonOptions;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.gen.structure.MapGenStronghold;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(value = ControllableStrongholdGen.class, remap = false)
public abstract class ControllableStrongholdGenMixin extends MapGenStronghold {
    @Shadow @Final private static double MAX_ANGLE;
    @Shadow @Final private static int COUNT;
    @Shadow @Final private static int MAX_DIST;
    @Shadow @Final private static int MIN_DIST;
    @Shadow @Final private static int PER_RING;

    @Shadow protected abstract void registerStrongholdChunk(int idx, int cx, int cz);

    /**
     * @author unilock
     * @reason allow disabling TerritoryStrongholdSystem - this simply restores the behavior that StrongholdLocationControl modifies with ASM. @Overwrite to fail hard
     */
    @Overwrite
    private void calculateLocations() {
        Random rand = new Random(worldObj.getSeed());

        int ox = DragonOptions.WORLDCENTERX.getValue();
        int oz = DragonOptions.WORLDCENTERZ.getValue();

        //BREAKS THINGS
        //ox = ox-500+rand.nextInt(1001);
        //oz = oz-500+rand.nextInt(1001);

        double ang = 0;
        double minAng = Math.min(10, MAX_ANGLE/4);

        int ringCount = 0;
        double rFactor = 1;
        for (int idx = 0; idx < COUNT; idx++) {
            double r = MIN_DIST+rand.nextInt(MAX_DIST-MIN_DIST+1);
            r *= rFactor;
            int cx = ox+(int)Math.round(Math.cos(ang)*r);
            int cz = oz+(int)Math.round(Math.sin(ang)*r);

            ChunkPosition biome = worldObj.getWorldChunkManager().findBiomePosition(cx+8, cz+8, 112, field_151546_e, rand);
            if (biome != null) {
                cx = biome.chunkPosX;
                cz = biome.chunkPosZ;
            }

            this.registerStrongholdChunk(idx, cx >> 4, cz >> 4);
            ringCount++;
            ang += minAng+(MAX_ANGLE-minAng)*rand.nextDouble();
            if (ang >= 360 || ringCount >= PER_RING) {
                rFactor *= CaveOptions.STRONGHOLDRINGSCALE.getDefaultFloat();
                ringCount = 0;
            }
        }
    }
}
