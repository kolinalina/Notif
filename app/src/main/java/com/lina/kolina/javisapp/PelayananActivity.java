package com.lina.kolina.javisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PelayananActivity extends AppCompatActivity {

   ImageView order2, complain2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayanan);

        order2 =(ImageView) findViewById(R.id.order);
        complain2 =(ImageView) findViewById(R.id.complain);

        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PelayananActivity.this, FormOrderActivity.class);
                startActivity(intent);

            }
        });

    }
}
