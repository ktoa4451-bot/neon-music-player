package com.example.musicplayer;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class MusicScreen extends Screen {
    public MusicScreen() {
        super(Text.literal("Neon Music Player"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int left = this.width / 2 - 140;
        int top = this.height / 2 - 90;

        context.fill(left, top, left + 280, top + 180, 0xEE101025);
        context.fill(left, top, left + 280, top + 3, 0xFF00E5FF);

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("NEON MUSIC PLAYER"),
            this.width / 2,
            top + 16,
            0xFF00FFFF
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Your music library"),
            this.width / 2,
            top + 48,
            0xFFFFFFFF
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Music scanner coming next"),
            this.width / 2,
            top + 78,
            0xFFAAAAFF
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
