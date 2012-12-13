package motivaatiovalaspeli;

import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 

/**
 * An external class that answers for playing the sound effects in this game.
 * 
 * TODO: Loading the sound files to a database and using them instead of
 * loading them from file every time a sound is played.
 * 
 * @author http://www.anyexample.com/programming/java/java_play_wav_sound_file.xml
 *
 */
 
public class AePlayWave extends Thread { 
 
    private String filename;
 
    private Position curPosition;
 
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 
 
    private enum Position { 
        LEFT, RIGHT, NORMAL
    }
 
    @SuppressWarnings("javadoc")
	public AePlayWave(String wavfile) { 
        this.filename = wavfile;
        this.curPosition = Position.NORMAL;
    } 
 
    @SuppressWarnings("javadoc")
	public AePlayWave(String wavfile, Position p) { 
        this.filename = wavfile;
        this.curPosition = p;
    } 
 
    @Override
	public void run() { 
 
        //File soundFile = new File(this.filename);

        /*if (!soundFile.exists()) { 
            System.err.println("Wave file not found: " + filename);
            return;
        } 
    */
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(this.filename));
        } catch (UnsupportedAudioFileException e1) { 
            e1.printStackTrace();
            return;
        } catch (IOException e1) { 
            e1.printStackTrace();
            return;
        } 
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try { 
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
            return;
        } catch (Exception e) { 
            e.printStackTrace();
            return;
        } 
 
        if (auline.isControlSupported(FloatControl.Type.PAN)) { 
            FloatControl pan = (FloatControl) auline
                    .getControl(FloatControl.Type.PAN);
            if (this.curPosition == Position.RIGHT) 
                pan.setValue(1.0f);
            else if (this.curPosition == Position.LEFT) 
                pan.setValue(-1.0f);
        } 
 
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[this.EXTERNAL_BUFFER_SIZE];
 
        try { 
            while (nBytesRead != -1) { 
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) 
                    auline.write(abData, 0, nBytesRead);
            } 
        } catch (IOException e) { 
            e.printStackTrace();
            return;
        } finally { 
            auline.drain();
            auline.close();
        } 
    } 
} 