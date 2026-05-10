package cc.unilock.dragonfixes.mixin.late.rotarycraft;

import Reika.RotaryCraft.Auxiliary.RecipeManagers.RecipesDryingBed;
import cc.unilock.dragonfixes.DragonFixesConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(value = RecipesDryingBed.class, remap = false)
public abstract class RecipesDryingBedMixin {
    @WrapOperation(method = "addPostLoadRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/oredict/OreDictionary;getOres(Ljava/lang/String;)Ljava/util/ArrayList;", ordinal = 0))
    private ArrayList<ItemStack> addPostLoadRecipes(String name, Operation<ArrayList<ItemStack>> original) {
        return original.call(DragonFixesConfig.replaceRubberWithGemLatex ? "gemLatex" : name);
    }
}
