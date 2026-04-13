package cc.unilock.dragonfixes.mixin.late.dragonapi;

import Reika.DragonAPI.Instantiable.IO.ControlledConfig;
import Reika.DragonAPI.Interfaces.Configuration.UserSpecificConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ControlledConfig.class, remap = false)
public class ControlledConfigMixin {
    /**
     * @author unilock
     * @reason allows forcing "client-specific" configs to use the same keys across all systems; @Redirect to fail hard
     */
    @Redirect(
        method = "getLabel",
        at = @At(
            value = "INVOKE",
            target = "LReika/DragonAPI/Interfaces/Configuration/UserSpecificConfig;isUserSpecific()Z"
        )
    )
    public boolean isUserSpecific$getLabel(UserSpecificConfig instance) {
        return false;
    }
}
