package sounds;

import handleds.Actor;

public class BackgroundMusicPlayer implements Actor {
    private AePlayWave backgroundMusicThread;
    private boolean alive;
    
    
    public BackgroundMusicPlayer(){
        this.alive = true;
        this.backgroundMusicThread = new AePlayWave("/data/taustamusiikki.wav");
        this.backgroundMusicThread.start();
    }
    

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public boolean inActivate() {
        return false;
    }

    @Override
    public boolean isDead() {
        return !this.alive;
    }

    @Override
    public boolean kill() {
        if (this.alive){
            this.alive = false;
            return true;
        } else return false;
    }

    @Override
    public void act() {
        if (!this.backgroundMusicThread.isAlive()){
            this.backgroundMusicThread = new AePlayWave("/data/KSS.wav");
            this.backgroundMusicThread.start();
        }
    }

}
