/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuUtils;

import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author otmangx
 */
public class GomokuAudioPlayer extends WavAudioPlayer{

    public GomokuAudioPlayer() throws LineUnavailableException {
        super();
    }
    public void playwinnerSound() {
        play("applause.wav");
    }
    
    public void playloserSound() {
        play("Sad-trumpet-sound.wav");
    }
    
    public void playdrawSound() {
        // not yet
    }

}
