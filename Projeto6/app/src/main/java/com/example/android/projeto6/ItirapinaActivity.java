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

public class ItirapinaActivity extends AppCompatActivity {

    private ArrayList<Tour> mTours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        // Create a list of tours
        mTours = new ArrayList<Tour>();

        Location saltao = new Location(getResources().getString(R.string.itirapina_name_1));
        saltao.setLatitude(-22.3928793);
        saltao.setLongitude(-47.8884396);
        mTours.add(new Tour(getResources().getString(R.string.itirapina_name_1),
                R.drawable.waterfall, saltao));

        Location monjolinho = new Location(getResources().getString(R.string.itirapina_name_2));
        monjolinho.setLatitude(-22.3943997);
        monjolinho.setLongitude(-47.8869641);
        mTours.add(new Tour(getResources().getString(R.string.itirapina_name_2),
                R.drawable.waterfall, monjolinho));

        Location ferradura = new Location(getResources().getString(R.string.itirapina_name_3));
        ferradura.setLatitude(-22.388961);
        ferradura.setLongitude(-47.886228);
        mTours.add(new Tour(getResources().getString(R.string.itirapina_name_3),
                R.drawable.waterfall, ferradura));

        Location palmeiras = new Location(getResources().getString(R.string.itirapina_name_4));
        palmeiras.setLatitude(-22.3809927);
        palmeiras.setLongitude(-47.8810192);
        mTours.add(new Tour(getResources().getString(R.string.itirapina_name_4),
                R.drawable.camping, palmeiras));

        Location fogao = new Location(getResources().getString(R.string.itirapina_name_5));
        fogao.setLatitude(-22.4271671);
        fogao.setLongitude(-47.8841896);
        mTours.add(new Tour(getResources().getString(R.string.itirapina_name_5),
                R.drawable.mountain, fogao));


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new TourAdapter(this, mTours));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double lat = mTours.get(position).getTourLocation().getLatitude();
                double lon = mTours.get(position).getTourLocation().getLongitude();
                String keyword = mTours.get(position).getTourDescription();
                Uri uri = Uri.parse("geo:" + lat + "," + lon + "?q=" + Uri.encode(keyword));

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }
}
