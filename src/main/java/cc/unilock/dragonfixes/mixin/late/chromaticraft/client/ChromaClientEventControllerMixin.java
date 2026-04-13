package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.ChromaClientEventController;
import cc.unilock.dragonfixes.DragonFixes;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import cpw.mods.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ChromaClientEventController.class, remap = false)
public class ChromaClientEventControllerMixin {
    /**
     * @author thegamemaster1234
     * @reason catch Exception
     */
    @WrapMethod(method = "updateGlowCliffRendering")
    private void updateGlowCliffRendering(TickEvent.ClientTickEvent evt, Operation<Void> original) {
        try {
            original.call(evt);
        } catch (Exception ignored) {
            DragonFixes.LOGGER.warn("Exception caught in ChromaClientEventController#updateGlowCliffRendering");
        }
    }
}
