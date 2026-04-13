package cc.unilock.dragonfixes;

import cpw.mods.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = DragonFixes.MOD_ID, version = Tags.VERSION, name = "DragonFixes", acceptedMinecraftVersions = "[1.7.10]", acceptableRemoteVersions = "*", dependencies = "after:CaveControl;"+"after:ChromatiCraft;"+"after:DragonRealmCore;"+"after:gtnhlib;")
public class DragonFixes {
    public static final String MOD_ID = "dragonfixes";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
}
