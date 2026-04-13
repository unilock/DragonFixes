package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.CrystalBlock;
import Reika.ChromatiCraft.Base.TileEntity.TileEntityAdjacencyUpgrade;
import Reika.ChromatiCraft.Magic.CrystalPotionController;
import Reika.ChromatiCraft.Registry.CrystalElement;
import Reika.ChromatiCraft.TileEntity.AOE.Effect.TileEntityPlayerDelegate;
import cc.unilock.dragonfixes.mixin.late.chromaticraft.accessor.CrystalBlockAccessor;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityPlayerDelegate.class, remap = false)
public abstract class TileEntityPlayerDelegateMixin extends TileEntityAdjacencyUpgrade {
    /**
     * @author unilock
     * @reason skip check for player being in radius of CrystalBlock
     */
    @Redirect(method = "tickDirection", at = @At(value = "INVOKE", target = "LReika/ChromatiCraft/Base/CrystalBlock;updateEffects(Lnet/minecraft/world/World;III)V"))
    private void tickDirection(CrystalBlock c, World world, int dx, int dy, int dz, @Local CrystalElement e) {
        // make sure the player is connected - avoids a NPE for FakePlayers...
        if (!(this.getPlacer() instanceof EntityPlayerMP player) || player.playerNetServerHandler != null) {
            EntityPlayer ep = this.getPlacer();
            int duration = c.getDuration(e);
            int level = c.getPotionLevel(e);
            float slugPower = ((CrystalBlockAccessor) c).callGetSlugPower(ep);
            if (slugPower > 0) {
                duration *= 1-0.2*slugPower;
                level += slugPower;
            }
            CrystalPotionController.instance.applyEffectFromColor(duration, level, ep, e, true);
        }
    }
}
