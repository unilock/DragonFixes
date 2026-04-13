package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.World.IWG.DungeonGenerator;
import Reika.DragonAPI.IO.ReikaFileReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;

@Mixin(value = DungeonGenerator.class, remap = false)
public class DungeonGeneratorMixin {
    @Unique
    private String cachedPath;
    @Unique
    private File cachedWorldDirectory;

    /**
     * @author FlamingKetchup
     * @reason cache the world directory path; @Redirect to fail hard
     */
    @Redirect(
        method = "updateNoisemaps",
        at = @At(
            value = "INVOKE",
            target = "LReika/DragonAPI/IO/ReikaFileReader;getRealPath(Ljava/io/File;)Ljava/lang/String;"
        )
    )
    private String updateNoisemaps$getRealPath(File f) {
        if (cachedPath == null || cachedWorldDirectory != f) {
            cachedPath = ReikaFileReader.getRealPath(f);
            cachedWorldDirectory = f;
        }
        return cachedPath;
    }
}
