package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.Auxiliary.Ability.WarpPoint;
import Reika.ChromatiCraft.Auxiliary.Ability.WarpPointData;
import Reika.ChromatiCraft.ChromatiCraft;
import Reika.DragonAPI.DragonAPICore;
import Reika.DragonAPI.IO.ReikaFileReader;
import Reika.DragonAPI.Instantiable.Data.Immutable.WorldLocation;
import cc.unilock.dragonfixes.mixin.late.chromaticraft.client.accessor.WarpPointAccessor;
import com.google.common.base.Charsets;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

@Mixin(value = WarpPointData.class, remap = false)
public class WarpPointDataMixin {
    /**
     * @author unilock
     * @reason Xaero's maps support
     */
    @ModifyReturnValue(method = "loadMiniMaps", at = @At("TAIL"))
    private static Collection<WarpPoint> loadMiniMaps(Collection<WarpPoint> original) {
        original.addAll(readXaero());
        return original;
    }

    @Unique
    @SideOnly(Side.CLIENT)
    private static Collection<WarpPoint> readXaero() {
        HashSet<WarpPoint> map = new HashSet<>();
        File xaeroWaypoints = new File(DragonAPICore.getMinecraftDirectory(), "XaeroWaypoints");
        if (xaeroWaypoints.exists() && xaeroWaypoints.isDirectory()) {
            for (File save : xaeroWaypoints.listFiles()) {
                // TODO: verify save.getName() contains current world name / server address
                if (save.isDirectory()) {
                    for (File dim : save.listFiles()) {
                        if (dim.isDirectory()) {
                            for (File waypoints : ReikaFileReader.getAllFilesInFolder(dim, ".txt")) {
                                readXaeroFile(dim.getName().substring(4), waypoints, map);
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    @Unique
    @SideOnly(Side.CLIENT)
    private static void readXaeroFile(String dim, File f, HashSet<WarpPoint> map) {
        int idx = 0;
        for (String line : ReikaFileReader.getFileAsLines(f, true, Charsets.UTF_8)) {
            if (line.startsWith("waypoint:")) {
                String data = line.substring(9);
                String[] parts = data.split(":");
                String label = parts[0];
                String x = parts[2];
                String y = parts[3];
                String z = parts[4];
                try {
                    WarpPoint p = WarpPointAccessor.create("["+(idx++)+"] "+label, new WorldLocation(Integer.parseInt(dim), Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z)));
                    map.add(p);
                } catch (Exception e) {
                    ChromatiCraft.logger.logError("Could not parse waypoint entry: "+data);
                    e.printStackTrace();
                }
            }
        }
    }
}
