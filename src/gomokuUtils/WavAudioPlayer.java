/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author otmangx
 */
class WavAudioPlayer implements AudioPlayer{
    public static String rsrcFolder = "/ressources/sounds/";
    Clip clip;
    

    public WavAudioPlayer() throws LineUnavailableException {
        clip = AudioSystem.getClip();
        
    }

    @Override
    public synchronized void play(final String url) {
        new Thread(new Runnable() {
          public void run() {
            try {
              AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(rsrcFolder + url));
              clip.open(inputStream);
              clip.start(); 
            } catch (Exception e) {
              System.err.println(e.getMessage());
            }
          }
        }).start();
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
