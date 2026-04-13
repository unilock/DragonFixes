package cc.unilock.dragonfixes.mixin.late.dragonapi;

import Reika.DragonAPI.Instantiable.Data.BlockStruct.AbstractSearch;
import Reika.DragonAPI.Instantiable.Data.Immutable.Coordinate;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;

import java.util.LinkedList;

@Mixin(value = AbstractSearch.FoundPath.class, remap = false)
public class AbstractSearchFoundPathMixin {
    /**
     * @author unilock
     * @reason catch NullPointerException
     */
    @WrapMethod(method = "getPath")
    private LinkedList<Coordinate> getPath(Operation<LinkedList<Coordinate>> original) {
        try {
            return original.call();
        } catch (NullPointerException ignored) {
            return null;
        }
    }
}
