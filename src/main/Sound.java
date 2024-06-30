package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {

        this.soundURL[0] = getClass().getResource("/sounds/house.wav");
        this.soundURL[1] = getClass().getResource("/sounds/tuto.wav");
        this.soundURL[2] = getClass().getResource("/sounds/mainTheme.wav");
        this.soundURL[3] = getClass().getResource("/sounds/chest.wav");
        this.soundURL[4] = getClass().getResource("/sounds/keyTaken.wav");
        this.soundURL[5] = getClass().getResource("/sounds/Select_001.wav");
        this.soundURL[6] = getClass().getResource("/sounds/lilSound.wav");

    } // Sound()

    public void setClip(final int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch(Exception e) {

        } // try/catch

    } // setClip(.)

    public void play() {

        this.clip.start();

    } // play()

    public void stop() {

        this.clip.stop();

    } // stop()

    public void loop() {

        this.clip.loop(Clip.LOOP_CONTINUOUSLY);

    } // loop()
} // Sound
