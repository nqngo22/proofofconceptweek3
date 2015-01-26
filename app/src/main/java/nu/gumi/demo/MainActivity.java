package nu.gumi.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import nu.gumi.demo.util.AudioPlayer;
import nu.gumi.demo.util.MapFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ActivityMainContainer, new PlaceholderFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {

        final static int reqCode = 1;
        private AudioPlayer mPlayer = new AudioPlayer();
        ArrayList<Integer> audioResourceId=new ArrayList<Integer>();

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button mPlayButton = (Button) rootView.findViewById(R.id.PlayWavButton);
            mPlayButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    List<String> WavList = new ArrayList<String>();
                    final Field[] fields=R.raw.class.getFields();
                    for(int count=0; count < fields.length; count++){
                        WavList.add(fields[count].getName());
                    }
                    final CharSequence[] WavSequence = WavList.toArray(new CharSequence[WavList.size()]);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Pick a sound file");
                    builder.setItems(WavSequence, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int choice) {
                            try {
                                mPlayer.play(getActivity(), fields[choice].getInt(fields[choice]));
                            } catch (IllegalAccessException e) {}
                        }
                    });
                    builder.show();
                }
            });

            Button mLoadMapButton = (Button) rootView.findViewById(R.id.LoadMapButton);
            mLoadMapButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fragment = new MapFragment();
                    fm.beginTransaction().replace(R.id.ActivityMainContainer, fragment).commit();
                }
            });

            return rootView;
        }
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);
    }




}
