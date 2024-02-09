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
package io.github.insertdead.remap_menu_key.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class RemapMenuKeyClient implements ClientModInitializer {
    public static KeyBinding menuBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.remap_menu_key.escape",
            InputUtil.Type.KEYSYM,
            InputUtil.GLFW_KEY_ESCAPE,
            KeyBinding.MISC_CATEGORY
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (menuBinding.wasPressed() && client.player != null) {
                boolean bl3 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.GLFW_KEY_F3);
                client.openGameMenu(bl3);
            }
        });
    }
}
