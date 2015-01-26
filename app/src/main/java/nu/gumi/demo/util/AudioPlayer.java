package nu.gumi.demo.util;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

import java.lang.reflect.Field;

import nu.gumi.demo.R;


/**
 * Created by Nguyen on 1/24/2015.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;
    public void stop() {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    public void play(Context c, int resourceID) {
        mPlayer = MediaPlayer.create(c, resourceID);
        mPlayer.start();
    }
}
