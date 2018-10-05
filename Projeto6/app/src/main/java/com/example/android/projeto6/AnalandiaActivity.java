package com.example.android.projeto6;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AnalandiaActivity extends AppCompatActivity {

    private ArrayList<Tour> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        // Create a list of tours
        tours = new ArrayList<Tour>();

        Location bocaina = new Location(getResources().getString(R.string.analandia_name_1));
        bocaina.setLatitude(-22.0846088);
        bocaina.setLongitude(-47.7313244);
        tours.add(new Tour(getResources().getString(R.string.analandia_name_1),
                R.drawable.waterfall, bocaina));

        Location escorrega = new Location(getResources().getString(R.string.analandia_name_2));
        escorrega.setLatitude(-22.1271308);
        escorrega.setLongitude(-47.6728506);
        tours.add(new Tour(getResources().getString(R.string.analandia_name_2),
                R.drawable.camping, escorrega));

        Location cuscuzeiro = new Location(getResources().getString(R.string.analandia_name_3));
        cuscuzeiro.setLatitude(-22.1169905);
        cuscuzeiro.setLongitude(-47.6953646);
        tours.add(new Tour(getResources().getString(R.string.analandia_name_3),
                R.drawable.mountain, cuscuzeiro));

        Location camelo = new Location(getResources().getString(R.string.analandia_name_4));
        camelo.setLatitude(-22.1113695);
        camelo.setLongitude(-47.6909964);
        tours.add(new Tour(getResources().getString(R.string.analandia_name_4),
                R.drawable.mountain, camelo));

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new TourAdapter(this, tours));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double lat = tours.get(position).getTourLocation().getLatitude();
                double lon = tours.get(position).getTourLocation().getLongitude();
                String keyword = tours.get(position).getTourDescription();
                Uri uri = Uri.parse("geo:" + lat + "," + lon + "?q=" + Uri.encode(keyword));

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }
}
