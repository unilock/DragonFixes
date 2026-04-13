package cc.unilock.dragonfixes.mixin.late.dragonrealmcore.stronghold;

import Reika.DragonRealmCore.Command.StrongholdSeedMixCommand;
import net.minecraft.command.ICommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(StrongholdSeedMixCommand.class)
public class StrongholdSeedMixCommandMixin {
    /**
     * @author unilock
     * @reason allow disabling TerritoryStrongholdSystem; @Overwrite to fail hard
     */
    @Overwrite
    public void processCommand(ICommandSender ics, String[] args) {
        // NO-OP
    }
}
