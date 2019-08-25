package com.lina.kolina.javisapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormOrderActivity extends AppCompatActivity {
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText proyek, deskripsi, lokasi,idclient,deadline;
    Button btnorder;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_order);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_idclient =  data.getStringExtra("id_client");
        String intent_idorder = data.getStringExtra("id_order");
        String intent_proyek = data.getStringExtra("nama_proyek");
        String intent_deskripsi = data.getStringExtra("deskripsi_proyek");
        String intent_lokasi = data.getStringExtra("lokasi_proyek");
        String intent_deadline = data.getStringExtra("deadline");

        /*end get data from intent*/


        idclient = findViewById(R.id.etClient);
        deadline = findViewById(R.id.etDeadline);
        proyek = findViewById(R.id.etProyek);
        deskripsi = findViewById(R.id.etDeskripsiPro);
        lokasi = findViewById(R.id.etLokasiPro);
        btnorder = findViewById(R.id.btnOrder);
        pd = new ProgressDialog(FormOrderActivity.this);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        deadline.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormOrderActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnorder.setText("Update Data");
            idclient.setText(intent_idclient);
//            idclient.setVisibility(View.GONE);
            proyek.setText(intent_proyek);
            deskripsi.setText(intent_deskripsi);
            lokasi.setText(intent_lokasi);
            deadline.setText(intent_deadline);

        }

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });


//
//        btnorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pesan1 = proyek.getText().toString();
//                String pesan2 = deskripsi.getText().toString();
//                String pesan3 = lokasi.getText().toString();
//
//                String semuapesan = "Nama Proyek: " + pesan1 + "\n" + "Deksripsi : " + pesan2 + "\n" + "Lokasi : " + pesan3;
//
//                Intent kirimWA = new Intent(Intent.ACTION_SEND);
//                kirimWA.setType("text/plain");
//                kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
//                kirimWA.putExtra("jid", "6285712351319" + "@s.whatsapp.net");
//                kirimWA.setPackage("com.whatsapp");
//
//                startActivity(kirimWA);
//            }
//        });

    }

    private void Update_data() {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, URLs.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(FormOrderActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(FormOrderActivity.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(FormOrderActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_client",idclient.getText().toString());
                map.put("nama_proyek",proyek.getText().toString());
                map.put("lokasi_proyek",lokasi.getText().toString());
                map.put("deskripsi_proyek",deskripsi.getText().toString());
                map.put("deadline",deadline.getText().toString());

                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(updateReq);
    }

    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, URLs.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(FormOrderActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(FormOrderActivity.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(FormOrderActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_client",idclient.getText().toString());
                map.put("nama_proyek",proyek.getText().toString());
                map.put("lokasi_proyek",lokasi.getText().toString());
                map.put("deskripsi_proyek",deskripsi.getText().toString());
                map.put("deadline",deadline.getText().toString());

                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(sendData);
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        deadline.setText(sdf.format(myCalendar.getTime()));
    }
}
