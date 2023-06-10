package io.github.insertdead.remap_menu_key.mixin;

import io.github.insertdead.remap_menu_key.MenuRemapClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
	@Inject(method = "keyPressed(III)Z", at = @At("HEAD"))
	void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> ci) {
		MinecraftClient client = ((HandledScreen) (Object) this).getClient();
		if (MenuRemapClient.keyBinding.matchesKey(keyCode, scanCode) && client.player != null) {
			client.player.closeScreen();
		}
	}
}
