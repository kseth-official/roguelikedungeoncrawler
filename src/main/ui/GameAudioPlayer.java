package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// A class that handles the game audio functionality.
public class GameAudioPlayer implements LineListener {
    private boolean soundPlayCompleted;

    public void play(String filePath) {
        File audioFile = new File(filePath);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info information = new DataLine.Info(Clip.class,audioFormat);
            Clip audioClip = (Clip) AudioSystem.getLine(information);
            audioClip.addLineListener(this);
            audioClip.open(audioInputStream);
            audioClip.start();
            while (!soundPlayCompleted) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Sound related interrupt should not have occurred!");
                }
            }
            audioClip.close();
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
        } catch (IOException ex) {
            // unknown exception
        }
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            soundPlayCompleted = true;
        }
    }
}
