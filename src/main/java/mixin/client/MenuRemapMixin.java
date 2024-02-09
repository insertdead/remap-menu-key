/*
 *    Remap Menu	Key, a Minecraft Mod to remap the menu (escape) key.
 *    Copyright (C) 2024 insertdead
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package mixin.client;

import io.github.insertdead.remap_menu_key.client.RemapMenuKeyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Adds
@Mixin(Screen.class)
abstract public class MenuRemapMixin {
	@Inject(method = "keyPressed(III)Z", at = @At("HEAD"), cancellable = true)
	void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (RemapMenuKeyClient.menuBinding.matchesKey(keyCode, scanCode)) {
			if (client.currentScreen == null) {
				client.openGameMenu(false);
			} else if (this.shouldCloseOnEsc()) {
				this.close();
			}

			ci.setReturnValue(true);
		} else if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
			ci.setReturnValue(false);
		}
	}

	@Shadow
	public void close() {
	}

	@Shadow
	public boolean shouldCloseOnEsc() {
		return true;
	}
}
