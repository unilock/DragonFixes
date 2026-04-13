package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.Magic.Artefact.ArtefactSpawner;
import cc.unilock.dragonfixes.DragonFixesConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ArtefactSpawner.class, remap = false)
public class ArtefactSpawnerMixin {
    /**
     * @author unilock
     * @reason allow disabling the shader via config
     */
    @Inject(method = "isShaderActive", at = @At("HEAD"), cancellable = true)
    private void isShaderActive(CallbackInfoReturnable<Boolean> cir) {
        if (DragonFixesConfig.disableArtefactShader) cir.setReturnValue(false);
    }
}
