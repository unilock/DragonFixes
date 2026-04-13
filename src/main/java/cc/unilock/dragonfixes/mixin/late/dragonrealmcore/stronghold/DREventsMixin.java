package cc.unilock.dragonfixes.mixin.late.dragonrealmcore.stronghold;

import Reika.DragonRealmCore.DREvents;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DREvents.class, remap = false)
public class DREventsMixin {
    /**
     * @author unilock
     * @reason allow disabling TerritoryStrongholdSystem; @Overwrite to fail hard
     */
    @Overwrite
    @SubscribeEvent
    public void loadChunk(ChunkEvent.Load event) {
        // NO-OP
    }
}
