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
