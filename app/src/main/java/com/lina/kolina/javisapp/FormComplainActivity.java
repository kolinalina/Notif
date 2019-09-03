package com.lina.kolina.javisapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormComplainActivity extends AppCompatActivity {
    EditText idorder, idkomplain, idclient,detail;
    Button btnkomplain;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_complain);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_idclient =  data.getStringExtra("id_client");
        String intent_idorder = data.getStringExtra("id_order");
        String intent_idkomplain = data.getStringExtra("id_komplain");
        String intent_detail = data.getStringExtra("detail_komplain");

        /*end get data from intent*/


        idclient = findViewById(R.id.etClientt);
        idorder = findViewById(R.id.etOrderr);
        detail = findViewById(R.id.etDetail);
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
       
        btnkomplain = findViewById(R.id.btnComplain);
        pd = new ProgressDialog(FormComplainActivity.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnkomplain.setText("Update Data");
            idclient.setText(intent_idclient);
            idclient.setVisibility(View.GONE);
            idorder.setText(intent_idorder);
            idorder.setVisibility(View.GONE);
            detail.setText(intent_detail);

        }
        idclient.setText(String.valueOf(user.getId_client()));


        btnkomplain.setOnClickListener(new View.OnClickListener() {
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

    }

    private void simpanData() {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, URLs.URL_INSERT2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(FormComplainActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(FormComplainActivity.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(FormComplainActivity.this, "pesan : Berhasil Input Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_client",idclient.getText().toString());
                map.put("id_order",idorder.getText().toString());
                map.put("detail_komplain",detail.getText().toString());

                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(sendData);
    }


    private void Update_data() {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, URLs.URL_UPDATE2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(FormComplainActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(FormComplainActivity.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(FormComplainActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_client",idclient.getText().toString());
                map.put("id_order",idorder.getText().toString());
                map.put("detail_komplain",detail.getText().toString());

                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(updateReq);
    }
}
