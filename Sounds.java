package Pong;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Julian on 22.11.2014.
 */
public class Sounds {

    private Clip clipHit;
    private Clip clipWall;
    private Clip clipFail;

    public void playShieldHit() {
        try {
            clipHit = AudioSystem.getClip();
            clipHit.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("pongHigh.wav")));
            ((FloatControl) clipHit.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
            clipHit.start();
        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
    }


    public void playWallHit() {
        try {
            clipWall = AudioSystem.getClip();
            clipWall.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("pongLow.wav")));
            ((FloatControl) clipWall.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
            clipWall.start();
        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
    }

    public void playFail() {
        try {
            clipFail = AudioSystem.getClip();
            clipFail.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("fail.wav")));
            ((FloatControl) clipFail.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
            clipFail.start();
        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
    }
}

