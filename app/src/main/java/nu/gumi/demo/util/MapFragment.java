package nu.gumi.demo.util;

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
        mapView.setImageResource(R.drawable.twodimensionalterrain);
        return v;
    }
}
