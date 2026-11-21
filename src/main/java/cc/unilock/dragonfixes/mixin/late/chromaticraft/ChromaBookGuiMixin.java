package cc.unilock.dragonfixes.mixin.late.chromaticraft;

import Reika.ChromatiCraft.Base.ChromaBookGui;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ChromaBookGui.class)
public abstract class ChromaBookGuiMixin extends GuiScreen {

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar,keyCode);
        if (keyCode == 1) {
            ChromaBookGui.lastGui = (ChromaBookGui) (Object) this;
        }
    }
}
