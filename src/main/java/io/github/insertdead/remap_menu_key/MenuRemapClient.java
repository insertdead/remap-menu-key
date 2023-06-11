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
package io.github.insertdead.remap_menu_key;

import java.lang.Override;

import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class MenuRemapClient implements ClientModInitializer {

	public static KeyBind keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBind(
		"key.remap_menu_key.escape",
		InputUtil.Type.KEYSYM,
		GLFW.GLFW_KEY_CAPS_LOCK,
		KeyBind.MISC_CATEGORY
	));

	@Override
	public void onInitializeClient(ModContainer mod) {
		MenuRemap.LOGGER.debug("Registered keybind for swapping escape key");

		ClientTickEvents.END.register(client -> {
			if (keyBinding.wasPressed() && client.player != null) {

				boolean bl3 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3);
				client.openPauseMenu(bl3);
			}
		});

	}
}
