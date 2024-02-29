package com.example.its.citymap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.maps.MapFragment;
import java.util.Arrays;

public class CityListFragment extends Fragment {

    public CityListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        ArrayAdapter<String> adapter =
        new ArrayAdapter<>(
            this.getActivity(),
            android.R.layout.simple_list_item_1,
            Arrays.asList(
                    "My Location",
                    "Dublin", "Kerry",
                    "Belfast", "Cork",
                    "Galway", "Wexford"
            )
        );

        ListView list = (ListView) view.findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> list, View row, int index, long rowID) {
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            MapFragment frag = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment2);
                            frag.getMapAsync((CityMap)getActivity());
                            ((CityMap)getActivity()).setCity((String)list.getItemAtPosition(index));
                        }
                        else {
                            ((CityMap)getActivity()).setCity((String)list.getItemAtPosition(index));
                            ((CityMap)getActivity()).showMap();
                        }
                    }
                }
        );
        return view;
    }
}
