package io.github.tubnom.mods.tubnettweaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ReloadableResourceManagerImpl.class)
public class ResourceManagerMixin {
	@Inject(at = @At("HEAD"), method = "reload")
	public void reload(Executor prepareExecutor, Executor applyExecutor, CompletableFuture<Unit> initialStage, List<ResourcePack> packs, CallbackInfoReturnable<ResourceReload> ci) {
		ServerInfo entry = MinecraftClient.getInstance().getCurrentServerEntry();
		if (entry == null) return;
		if (entry.address.endsWith("tubnet.gg")) {
			final List<ResourcePack> packs1 = new ArrayList<>(packs);
			for (ResourcePack pack : packs1) {
				if (pack.getName().contains("Fabric Mods")) {
					packs1.remove(packs1.indexOf(pack));
					packs1.add(packs1.size(), pack);
					break;
				}
			}
			packs = packs1;
		}
	}
}
