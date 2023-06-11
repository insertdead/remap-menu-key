/*
	Remap Menu Key
	Copyright (C) 2023 insertdead

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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
