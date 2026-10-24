package com.example.musicplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    private static final Path CONFIG = Paths.get(
        "config", "music-player", "settings.properties"
    );

    private static final Properties DATA = new Properties();

    public static void load() {
        try {
            if (Files.exists(CONFIG)) {
                try (var input = Files.newInputStream(CONFIG)) {
                    DATA.load(input);
                }
            }
        } catch (IOException e) {
            System.err.println("Music Player: could not load settings.");
        }
    }

    public static float getVolume() {
        try {
            return Math.max(0.0f, Math.min(1.0f,
                Float.parseFloat(DATA.getProperty("volume", "0.7"))));
        } catch (NumberFormatException e) {
            return 0.7f;
        }
    }

    public static void setVolume(float volume) {
        DATA.setProperty("volume",
            String.valueOf(Math.max(0.0f, Math.min(1.0f, volume))));
        save();
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG.getParent());
            try (var output = Files.newOutputStream(CONFIG)) {
                DATA.store(output, "Neon Music Player settings");
            }
        } catch (IOException e) {
            System.err.println("Music Player: could not save settings.");
        }
    }
}
