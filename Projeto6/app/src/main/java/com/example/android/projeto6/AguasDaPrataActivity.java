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

public class AguasDaPrataActivity extends AppCompatActivity {

    private ArrayList<Tour> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        // Create a list of tours
        tours = new ArrayList<Tour>();

        Location paiol = new Location(getResources().getString(R.string.AguasDaPrata_name_1));
        paiol.setLatitude(-21.933162);
        paiol.setLongitude(-46.7049004);
        tours.add(new Tour(getResources().getString(R.string.AguasDaPrata_name_1),
                R.drawable.camping, paiol));

        Location picoGaviao = new Location(getResources().getString(R.string.AguasDaPrata_name_2));
        picoGaviao.setLatitude(-22.0151293);
        picoGaviao.setLongitude(-46.6258772);
        tours.add(new Tour(getResources().getString(R.string.AguasDaPrata_name_2),
                R.drawable.mountain, picoGaviao));

        Location setimaqueda = new Location(getResources().getString(R.string.AguasDaPrata_name_3));
        setimaqueda.setLatitude(-21.9184716);
        setimaqueda.setLongitude(-46.691119);
        tours.add(new Tour(getResources().getString(R.string.AguasDaPrata_name_3),
                R.drawable.waterfall, setimaqueda));

        Location cascatinha = new Location(getResources().getString(R.string.AguasDaPrata_name_4));
        cascatinha.setLatitude(-21.9373053);
        cascatinha.setLongitude(-46.7065691);
        tours.add(new Tour(getResources().getString(R.string.AguasDaPrata_name_4),
                R.drawable.waterfall, cascatinha));

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
