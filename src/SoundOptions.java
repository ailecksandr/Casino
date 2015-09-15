import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.Serializable;

/**
 * Created by Notebook on 14.04.2015.
 */
public class SoundOptions implements Serializable{

    private Boolean soundEnabled;
    private Clip clMusic, clAudio, clSound;
    private String musicDestination;

    public SoundOptions(String musicDestination){
        soundEnabled=true;
        this.musicDestination=musicDestination;

        try{
            File musicSource=new File(musicDestination);
            File audioSource=new File("source/Sound/Active.wav");
            AudioInputStream music = AudioSystem.getAudioInputStream(musicSource),
                             audio = AudioSystem.getAudioInputStream(audioSource);

            clMusic = AudioSystem.getClip();
            clMusic.open(music);
            clMusic.loop(Clip.LOOP_CONTINUOUSLY);
            clMusic.start();

            clAudio = AudioSystem.getClip();
            clAudio.open(audio);

            music.close();
            audio.close();
            musicSource=null;
            audioSource=null;
        }
        catch (Exception e){}
    }

    public void buttonSound(){
        try{
            clAudio.start();
            clAudio.setFramePosition(0);
        }
        catch (Exception ex){}
    }

    public void createSound(String soundDestination){
        try{
            File soundSource=new File(soundDestination);
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundSource);

            clSound = AudioSystem.getClip();
            clSound.open(sound);
            clSound.start();
            clSound.setFramePosition(0);

            sound.close();
        }
        catch (Exception e){}
    }

    public Boolean isEnabled(){
        return soundEnabled;
    }

    private void stopSound(){
        soundEnabled=false;
        clMusic.stop();
        clMusic.setFramePosition(0);
    }

    private void startSound(){
        soundEnabled=true;
        clMusic.loop(Clip.LOOP_CONTINUOUSLY);
        clMusic.start();
    }

    public void changeMusic(String musicDestination){
        try {
            File musicSource = new File(musicDestination);
            AudioInputStream music = AudioSystem.getAudioInputStream(musicSource);
            clMusic.close();
            clMusic = AudioSystem.getClip();
            clMusic.open(music);
            if (isEnabled()){
                clMusic.loop(Clip.LOOP_CONTINUOUSLY);
                clMusic.start();
        }

            music.close();
            musicSource=null;
        }
        catch(Exception e){};
    }

    public void setMusicDestination(String musicDestination){
        this.musicDestination=musicDestination;
    }

    public String getMusicDestination(){
        return musicDestination;
    }

    public void changeSound(){
        if (soundEnabled) stopSound();
        else startSound();
    }

}
