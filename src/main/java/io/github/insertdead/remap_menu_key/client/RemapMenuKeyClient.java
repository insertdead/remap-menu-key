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
            InputUtil.GLFW_KEY_CAPS_LOCK,
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
