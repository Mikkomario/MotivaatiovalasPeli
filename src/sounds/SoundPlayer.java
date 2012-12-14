package sounds;

import java.util.Random;

/**
 * Class that plays the sound effects in the game. Uses objects from the external
 * class AePlayWave.
 * 
 *
 */

public class SoundPlayer {
    private long whenLastPlayedSound;
    private static final Random rand = new Random();
    private static final String[] pathsToValasSounds = {
        "/data/EiSeMitaan.wav",
        "/data/KSS.wav",
        "/data/Flow.wav",
        "/data/Kaikki.wav"       
    };
    private static final String[] pathsToKuhaSounds = {
        "/data/Antaa.wav",
        "/data/EiMaksa.wav",
        "/data/EiOo.wav",
        "/data/EtOSaa.wav",
        "/data/Turha.wav"
    };
    
    /**
     * Creates a new soundplayer
     */
    public SoundPlayer() {
        // playing the sound should work first time whe using the method
        this.whenLastPlayedSound = System.currentTimeMillis() + 1501;
    }
    
    /**
     * Plays a random valas sound
     */
    public void playRandomValasSound() {
        if (System.currentTimeMillis() > this.whenLastPlayedSound + 1500){
            this.whenLastPlayedSound = System.currentTimeMillis();
            AePlayWave sound = new AePlayWave(SoundPlayer.pathsToValasSounds
                    [rand.nextInt(pathsToValasSounds.length)]);
            sound.start();
        }
    }

    /**
     * Plays a random kuha sound
     */
    public void playRandomKuhaSound() {
        if (System.currentTimeMillis() > this.whenLastPlayedSound + 1500){
            this.whenLastPlayedSound = System.currentTimeMillis();
            AePlayWave sound = new AePlayWave(SoundPlayer.pathsToKuhaSounds
                    [rand.nextInt(pathsToKuhaSounds.length)]);
            sound.start();
        }
    }
    
    /*
    public static void main(String[] args) {
        new SoundPlayer().playRandomValasSound();
    }
    */
}
