package com.example.musicplayer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.nio.file.Path;

public class MusicPlayer {
    private static Clip clip;

    public static void play(Path path) {
        stop();

        try {
            clip = AudioSystem.getClip();
            try (var audio = AudioSystem.getAudioInputStream(path.toFile())) {
                clip.open(audio);
            }

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                float volume = ConfigManager.getVolume();
                float db = volume <= 0.001f
                    ? gain.getMinimum()
                    : (float) (20.0 * Math.log10(volume));

                gain.setValue(Math.max(gain.getMinimum(),
                    Math.min(gain.getMaximum(), db)));
            }

            clip.start();
        } catch (Exception e) {
            System.err.println("Music Player: failed to play " + path);
            stop();
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }

    public static boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
