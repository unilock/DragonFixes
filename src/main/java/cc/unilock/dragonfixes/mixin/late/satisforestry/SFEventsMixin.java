package cc.unilock.dragonfixes.mixin.late.satisforestry;

import Reika.DragonAPI.Instantiable.Event.SlotEvent;
import Reika.Satisforestry.SFEvents;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = SFEvents.class, remap = false)
public class SFEventsMixin {
    /**
     * @author unilock
     * @reason check if the inventory is instanceof InventoryPlayer before casting
     */
    @WrapMethod(method = "onRemoveArmor")
    private void onRemoveArmor(SlotEvent.RemoveFromSlotEvent evt, Operation<Void> original) {
        if (evt.inventory instanceof InventoryPlayer) {
            original.call(evt);
        }
    }
}
