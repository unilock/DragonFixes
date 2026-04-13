package cc.unilock.dragonfixes.mixin.late.satisforestry;

import Reika.RotaryCraft.API.Power.ShaftPowerReceiver;
import Reika.Satisforestry.Blocks.BlockSFMultiBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockSFMultiBase.TileShaftConnection.class, remap = false)
public abstract class TileShaftConnectionMixin extends BlockSFMultiBase.TileMinerConnection {
    @Shadow
    public abstract boolean isReceiving();

    /**
     * @author thegamemaster1234, unilock
     * @reason fix instanceof checks to allow power hub to work on pressurizer
     */
    @Overwrite
    public void setOmega(int omega) {
        if (this.isReceiving()) {
            ((ShaftPowerReceiver)this.getRoot()).setOmega(omega);
        }
    }

    /**
     * @author thegamemaster1234, unilock
     * @reason fix instanceof checks to allow power hub to work on pressurizer
     */
    @Overwrite
    public void setTorque(int torque) {
        if (this.isReceiving()) {
            ((ShaftPowerReceiver)this.getRoot()).setTorque(torque);
        }
    }

    /**
     * @author thegamemaster1234, unilock
     * @reason fix instanceof checks to allow power hub to work on pressurizer
     */
    @Overwrite
    public void setPower(long power) {
        if (this.isReceiving()) {
            ((ShaftPowerReceiver)this.getRoot()).setPower(power);
        }
    }
}
