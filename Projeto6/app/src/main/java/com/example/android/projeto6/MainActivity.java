package com.example.android.projeto6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView analandia = (TextView) findViewById(R.id.analandia);
        analandia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent analandiaIntent = new Intent(MainActivity.this, AnalandiaActivity.class);
                startActivity(analandiaIntent);
            }
        });

        TextView brotas = (TextView) findViewById(R.id.brotas);
        brotas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent brotasIntent = new Intent(MainActivity.this, BrotasActivity.class);
                startActivity(brotasIntent);
            }
        });

        TextView aguasDaPrata = (TextView) findViewById(R.id.aguasDaPrata);
        aguasDaPrata.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aguasDaPrataIntent = new Intent(MainActivity.this, AguasDaPrataActivity.class);
                startActivity(aguasDaPrataIntent);
            }
        });

        TextView itirapina = (TextView) findViewById(R.id.itirapina);
        itirapina.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itirapinaIntent = new Intent(MainActivity.this, ItirapinaActivity.class);
                startActivity(itirapinaIntent);
            }
        });
    }
}
