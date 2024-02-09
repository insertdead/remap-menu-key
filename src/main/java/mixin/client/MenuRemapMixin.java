package mixin.client;

import io.github.insertdead.remap_menu_key.client.RemapMenuKeyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
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
    public void close() {}

    @Shadow
    public boolean shouldCloseOnEsc() {return true;}
}
