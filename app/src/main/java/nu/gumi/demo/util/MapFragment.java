package nu.gumi.demo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nu.gumi.demo.R;

/**
 * Created by Nguyen on 1/25/2015.
 */
public class MapFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, parent, false);

        ImageView mapView = (ImageView)v.findViewById(R.id.MapImageView);
        //mapView.setImageResource(R.drawable.twodimensionalterrain);

        Bitmap tiles = BitmapFactory.decodeResource(getResources(), R.drawable.twodimensionalterrain);
        int width = tiles.getWidth();
        Bitmap mBitmap = Bitmap.createBitmap(tiles, 0 , 0, width, width);
        Bitmap mBitmap2 = Bitmap.createBitmap(tiles, 0 , width, width, width);
        Bitmap bmOverlay = Bitmap.createBitmap(width * 40, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmOverlay);

            for(int j = 0; j < 40; j++){
                if(j % 2 == 0)
                    canvas.drawBitmap(mBitmap, j * width, 0, null);
                else
                    canvas.drawBitmap(mBitmap2, j * width, 0, null);
            }

        mapView.setImageBitmap(bmOverlay);


        return v;
    }
}
