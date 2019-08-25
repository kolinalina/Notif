package com.lina.kolina.javisapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {
    Button logoutt;
    TextView dataperusahaann, dataalamatt, dataemaill, datanohpp, usernamepro,idclientt;

    String nama_perusahaan, alamat, nama_client, email, no_hp, id_client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        logoutt = findViewById(R.id.logout);
        dataperusahaann = findViewById(R.id.dataperusahaan);
        dataalamatt = findViewById(R.id.dataalamat);
        dataemaill = findViewById(R.id.dataemail);
        datanohpp = findViewById(R.id.datanohp);
        usernamepro=findViewById(R.id.usernamepro);

        email = getIntent().getStringExtra("email");
        nama_perusahaan = getIntent().getStringExtra("nama_perusahaan");
        alamat = getIntent().getStringExtra("alamat");
        nama_client = getIntent().getStringExtra("nama_client");
        no_hp = getIntent().getStringExtra("no_hp");
        id_client = getIntent().getStringExtra("id_client");


        dataperusahaann.setText(nama_perusahaan);
        dataalamatt.setText(alamat);
        dataemaill.setText(email);
        datanohpp.setText(no_hp);
        usernamepro.setText(id_client);

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });



    }
}

