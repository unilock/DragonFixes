package cc.unilock.chromatifixes;

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
        return "mixins.chromatifixes.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final boolean cavecontrol = loadedMods.contains("CaveControl");
        final boolean chromaticraft = loadedMods.contains("ChromatiCraft");
        final boolean dragonapi = loadedMods.contains("DragonAPI");
        final boolean satisforestry = loadedMods.contains("Satisforestry");
        final boolean dragonrealmcore = loadedMods.contains("DragonRealmCore");
        final boolean reactorcraft = loadedMods.contains("ReactorCraft");

        List<String> mixins = new ArrayList<>();

        if (FMLLaunchHandler.side().isClient()) {
            if (chromaticraft) {
                mixins.add("chromaticraft.client.accessor.WarpPointAccessor");
                mixins.add("chromaticraft.client.ArtefactSpawnerMixin");
                mixins.add("chromaticraft.client.ChromaClientEventControllerMixin");
                mixins.add("chromaticraft.client.ChromaDimensionManagerMixin");
                mixins.add("chromaticraft.client.CrystalFurnaceHandlerMixin");
                mixins.add("chromaticraft.client.EnchantDecompHandlerMixin");
                mixins.add("chromaticraft.client.FabricatorHandlerMixin");
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
            mixins.add("chromaticraft.DungeonGeneratorMixin");
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
            if (ChromatiFixesConfig.disableClientSpecificConfigs) {
                mixins.add("dragonapi.ControlledConfigMixin");
            }
        }
        if (satisforestry) {
            mixins.add("satisforestry.SFEventsMixin");
            mixins.add("satisforestry.TileShaftConnectionMixin");
        }
        if (dragonrealmcore) {
            mixins.add("dragonrealmcore.BlockT2HexGeneratorMixin");
            mixins.add("dragonrealmcore.BlockT3HexGeneratorMixin");
            if (ChromatiFixesConfig.disableTerritoryStrongholdSystem) {
                mixins.add("cavecontrol.stronghold.ControllableStrongholdGenMixin");
                mixins.add("dragonrealmcore.stronghold.DREventsMixin");
                mixins.add("dragonrealmcore.stronghold.StrongholdSeedMixCommandMixin");
            }
            if (ChromatiFixesConfig.energizationManagerFix) {
                mixins.add("dragonrealmcore.EnergizationManagerMixin");
            }
        }
        if (reactorcraft) {
            mixins.add("reactorcraft.accessor.TileEntityHeatPipeAccessor");
            mixins.add("reactorcraft.accessor.TileEntityNuclearBoilerAccessor");
            mixins.add("reactorcraft.accessor.TileEntityReactorBaseAccessor");
            mixins.add("reactorcraft.accessor.TileEntityWaterCellAccessor");
            mixins.add("reactorcraft.TileEntityHeatPipeMixin");
            mixins.add("reactorcraft.TileEntityReactorBaseMixin");
            mixins.add("reactorcraft.TileEntityWaterCellMixin");
            mixins.add("reactorcraft.TileEntityCentrifugeMixin");
        }

        return mixins;
    }
}
