package cc.unilock.dragonfixes.mixin.late.dragonrealmcore.client;

import Reika.DragonAPI.IO.Shaders.ShaderProgram;
import Reika.DragonRealmCore.DREvents;
import cc.unilock.dragonfixes.DragonFixesConfig;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DREvents.class, remap = false)
public class DREventsMixin {
    /**
     * @author unilock
     * @reason allow disabling the shader via config
     */
    @Inject(method = "applyNetherShader", at = @At(value = "INVOKE_ASSIGN", target = "LReika/DragonRealmCore/DRClient;getNetherShader()LReika/DragonAPI/IO/Shaders/ShaderProgram;", shift = At.Shift.AFTER), cancellable = true)
    private void applyNetherShader(CallbackInfo ci, @Local ShaderProgram s) {
        if (DragonFixesConfig.disableNetherShader) {
            s.setIntensity(0.0F);
            s.setEnabled(false);
            ci.cancel();
        }
    }
}
