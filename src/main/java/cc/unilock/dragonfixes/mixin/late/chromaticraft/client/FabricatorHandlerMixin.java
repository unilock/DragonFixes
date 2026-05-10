package cc.unilock.dragonfixes.mixin.late.chromaticraft.client;

import Reika.ChromatiCraft.GUI.Tile.Inventory.GuiItemFabricator;
import Reika.ChromatiCraft.ModInterface.NEI.FabricatorHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;

@Mixin(value = FabricatorHandler.class, remap = false)
public abstract class FabricatorHandlerMixin extends TemplateRecipeHandler {
    /**
     * @author FlamingKetchup
     * @reason Allow opening NEI from Item Fabricator UI
     */
    @ModifyReturnValue(method = "getGuiClass", at = @At("RETURN"))
    private Class<? extends GuiContainer> getGuiClass(Class<? extends GuiContainer> original) {
        if (original == null) {
            return GuiItemFabricator.class;
        }
        return original;
    }

    /**
     * @author FlamingKetchup
     * @reason Remove client player openContainer instanceof check and adjust to proper location and size; @Overwrite to fail hard
     */
    @Overwrite
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(60, 4, 70, 56), "ccfabric"));
    }
}
