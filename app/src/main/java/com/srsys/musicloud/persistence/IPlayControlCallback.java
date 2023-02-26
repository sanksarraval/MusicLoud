package com.srsys.musicloud.persistence;


/**
 * ，Play behavior interface callback
 *
 * @author Admin
 */
public interface IPlayControlCallback {

    /**
     * play
     *
     * @return Whether the device is successfully played: true: The device is successfully played. false: fail;
     */
    boolean play();

    /**
     * play
     *
     * @param name The name of the music being played
     * @return Whether the device is successfully played: true: The device is successfully played. false: fail;
     */
    boolean play(String name);

    /**
     * Play the last song
     *
     * @return Whether the previous song is played successfully. true: successful. false: fail;
     */
    boolean playLast();

    /**
     * Play the next song
     *
     * @return Whether to play the next song successfully. true: successful. false: fail;
     */
    boolean playNext();

    /**
     * pause
     *
     * @return Whether to suspend successfully: true: succeeded. false: fail;
     */
    boolean pause();

    /**
     * Whether it is being played
     *
     * @return Is it playing? true is playing and false is paused
     */
    boolean isPlaying();

    /**
     * Get the playback progress
     *
     * @return The value of the playback progress
     */
    int getProgress();

    /**
     * Free the player's resources
     */
    void releasePlayer();
}
