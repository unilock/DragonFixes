package cc.unilock.dragonfixes;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class LateMixinLoader implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.dragonfixes.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final boolean cavecontrol = loadedMods.contains("CaveControl");
        final boolean chromaticraft = loadedMods.contains("ChromatiCraft");
        final boolean dragonapi = loadedMods.contains("DragonAPI");
        final boolean reactorcraft = loadedMods.contains("ReactorCraft");
        final boolean rotarycraft = loadedMods.contains("RotaryCraft");
        final boolean satisforestry = loadedMods.contains("Satisforestry");
        final boolean dragonrealmcore = loadedMods.contains("DragonRealmCore");

        List<String> mixins = new ArrayList<>();

        if (FMLLaunchHandler.side().isClient()) {
            if (chromaticraft) {
                mixins.add("chromaticraft.client.accessor.WarpPointAccessor");
                mixins.add("chromaticraft.client.ArtefactSpawnerMixin");
                mixins.add("chromaticraft.client.ChromaClientEventControllerMixin");
                mixins.add("chromaticraft.client.ChromaDimensionManagerMixin");
                mixins.add("chromaticraft.client.CrystalFurnaceHandlerMixin");
                mixins.add("chromaticraft.client.CrystalGlowRendererMixin");
                mixins.add("chromaticraft.client.EnchantDecompHandlerMixin");
                mixins.add("chromaticraft.client.FabricatorHandlerMixin");
                mixins.add("chromaticraft.client.GlowTreeRendererMixin");
                mixins.add("chromaticraft.client.WarpPointDataMixin");
            }
            if (dragonrealmcore) {
                mixins.add("dragonrealmcore.client.DREventsMixin");
            }
        }

        if (cavecontrol) {
            mixins.add("cavecontrol.CaveLoaderMixin");
        }
        if (chromaticraft) {
            mixins.add("chromaticraft.accessor.CrystalBlockAccessor");
            mixins.add("chromaticraft.accessor.TileEntityCrystalBroadcasterAccessor");
            mixins.add("chromaticraft.BlockChromaTileMixin");
            mixins.add("chromaticraft.BlockDecoFlowerMixin");
            mixins.add("chromaticraft.BlockTieredPlantMixin");
            mixins.add("chromaticraft.ChromaBlocksMixin");
            mixins.add("chromaticraft.CrystalTypeBlockMixin");
            mixins.add("chromaticraft.DungeonGeneratorMixin");
            mixins.add("chromaticraft.ItemBlockCrystalGlowMixin");
            mixins.add("chromaticraft.ProgressionManagerMixin");
            mixins.add("chromaticraft.PylonFinderMixin");
            mixins.add("chromaticraft.PylonGeneratorMixin");
            mixins.add("chromaticraft.TileEntityCrystalBroadcasterMixin");
            mixins.add("chromaticraft.TileEntityLumenAlvearyHumidityMatchingEffectMixin");
            mixins.add("chromaticraft.TileEntityLumenAlvearyTemperatureMatchingEffectMixin");
            mixins.add("chromaticraft.TileEntityPlayerDelegateMixin");
            mixins.add("chromaticraft.TileEntityWirelessPoweredMixin");
        }
        if (dragonapi) {
            mixins.add("dragonapi.AbstractSearchFoundPathMixin");
            if (DragonFixesConfig.disableClientSpecificConfigs) {
                mixins.add("dragonapi.ControlledConfigMixin");
            }
        }
        if (reactorcraft) {
            mixins.add("reactorcraft.BlockReactorTileMixin");
        }
        if (rotarycraft) {
            mixins.add("rotarycraft.BlockBasicMultiTEMixin");
            mixins.add("rotarycraft.RecipesDryingBedMixin");
        }
        if (satisforestry) {
            mixins.add("satisforestry.SFEventsMixin");
            mixins.add("satisforestry.TileShaftConnectionMixin");
        }
        if (dragonrealmcore) {
            mixins.add("dragonrealmcore.BlockT2HexGeneratorMixin");
            mixins.add("dragonrealmcore.BlockT3HexGeneratorMixin");
            if (DragonFixesConfig.disableTerritoryStrongholdSystem) {
                mixins.add("cavecontrol.stronghold.ControllableStrongholdGenMixin");
                mixins.add("dragonrealmcore.stronghold.DREventsMixin");
                mixins.add("dragonrealmcore.stronghold.StrongholdSeedMixCommandMixin");
            }
            if (DragonFixesConfig.energizationManagerFix) {
                mixins.add("dragonrealmcore.EnergizationManagerMixin");
            }
        }

        return mixins;
    }
}
