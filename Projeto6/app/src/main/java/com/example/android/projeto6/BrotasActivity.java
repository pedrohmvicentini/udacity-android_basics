package com.example.android.projeto6;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BrotasActivity extends AppCompatActivity {

    private ArrayList<Tour> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        // Create a list of tours
        tours = new ArrayList<Tour>();

        Location escorregador = new Location(getResources().getString(R.string.brotas_name_1));
        escorregador.setLatitude(-22.4011882);
        escorregador.setLongitude(-47.9431995);
        tours.add(new Tour(getResources().getString(R.string.brotas_name_1),
                R.drawable.camping, escorregador));

        Location jacare = new Location(getResources().getString(R.string.brotas_name_2));
        jacare.setLatitude(-22.2901925);
        jacare.setLongitude(-48.1332048);
        tours.add(new Tour(getResources().getString(R.string.brotas_name_2),
                R.drawable.camping, jacare));

        Location tresQuedas = new Location(getResources().getString(R.string.brotas_name_3));
        tresQuedas.setLatitude(-22.3515283);
        tresQuedas.setLongitude(-48.1307795);
        tours.add(new Tour(getResources().getString(R.string.brotas_name_3),
                R.drawable.waterfall, tresQuedas));

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
