package com.lina.kolina.javisapp;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrasiActivity extends AppCompatActivity {
    private Button btnregisterr;
    private TextView txtloginn;
    private EditText perusahaann, alamatt, nama_clientt, emaill, nohpp, passs;
    private ProgressBar loadingg;
//    private static String URL_REGIST = "http://192.168.43.132/android_register_login/register.php";
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);


        loadingg = findViewById(R.id.loading);


        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        
        perusahaann = findViewById(R.id.etPerusahaan);
        alamatt = findViewById(R.id.etAlamatPerusahaan);
        nama_clientt = findViewById(R.id.etFullname);
        emaill = findViewById(R.id.etEmail);
        nohpp = findViewById(R.id.etNohp);
        passs = findViewById(R.id.etPassword);


        btnregisterr = findViewById(R.id.btnRegister);
        txtloginn = findViewById(R.id.txtLogin);



        txtloginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RegistrasiActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btnregisterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();


            }

        });
    }

    private void registerUser() {
        final String nama_perusahaan = this.perusahaann.getText().toString().trim();
        final String alamat = this.alamatt.getText().toString().trim();
        final String nama_client = this.nama_clientt.getText().toString().trim();
        final String email = this.emaill.getText().toString().trim();
        final String no_hp = this.nohpp.getText().toString().trim();
        final String password = this.passs.getText().toString().trim();

        if (TextUtils.isEmpty(nama_perusahaan)) {
            perusahaann.setError("Please enter nama perusahaan");
            perusahaann.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(alamat)) {
            alamatt.setError("Please enter your alamat perusahaan");
           alamatt.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emaill.setError("Enter a valid email");
            emaill.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passs.setError("Enter a password");
           passs.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nama_client)) {
            nama_clientt.setError("Enter a nama client");
            nama_clientt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(no_hp)) {
            nohpp.setError("Enter a no hp");
            nohpp.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingg.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id_client"),
                                        userJson.getString("nama_perusahaan"),
                                        userJson.getString("alamat"),
                                        userJson.getString("email"),
                                        userJson.getString("nama_client"),
                                        userJson.getString("no_hp")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_perusahaan",nama_perusahaan);
                params.put("alamat",alamat);
                params.put("email",email);
                params.put("nama_client",nama_client);
                params.put("no_hp",no_hp);
                params.put("password",password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}