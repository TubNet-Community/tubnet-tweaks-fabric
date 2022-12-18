package io.github.tubnom.mods.tubnettweaks.mixin;

import net.fabricmc.fabric.api.resource.ModResourcePack;
import net.fabricmc.fabric.impl.resource.loader.FabricModResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Pseudo
@Mixin(FabricModResourcePack.class)
public class FabricModResourcePackMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), index = 2)
    private static List<ModResourcePack> inject(List<ModResourcePack> pack2) {
        ArrayList<ModResourcePack> packs = new ArrayList<>(pack2);
        for (ModResourcePack pack : packs) {
            if (pack.getName().equals("TubNet Tweaks")) {
                packs.remove(pack);
                break;
            }
        }
        return packs;
    }
}
