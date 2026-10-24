package com.example.musicplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TrackLoader {
    private static final Path MUSIC_DIR =
        Paths.get("config", "music-player", "music");

    public static List<Path> scan() {
        try {
            Files.createDirectories(MUSIC_DIR);

            try (Stream<Path> files = Files.list(MUSIC_DIR)) {
                return files
                    .filter(Files::isRegularFile)
                    .filter(TrackLoader::isSupported)
                    .sorted()
                    .toList();
            }
        } catch (IOException e) {
            System.err.println("Music Player: could not scan music folder.");
            return List.of();
        }
    }

    private static boolean isSupported(Path path) {
        String name = path.getFileName().toString().toLowerCase();
        return name.endsWith(".wav");
    }
}
