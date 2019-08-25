package com.lina.kolina.javisapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lina.kolina.javisapp.LoginActivity;
import com.lina.kolina.javisapp.MainActivity;
import com.lina.kolina.javisapp.ProfilActivity;
import com.lina.kolina.javisapp.R;
import com.lina.kolina.javisapp.SharedPrefManager;
import com.lina.kolina.javisapp.User;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment implements View.OnClickListener {
    Button logoutt;
    TextView dataperusahaann, dataalamatt, dataemaill, datanohpp, usernamepro,idclient;

    String nama_perusahaan, alamat, nama_client, email, no_hp;
    private Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {


            logoutt = view.findViewById(R.id.logout);
            dataperusahaann = view.findViewById(R.id.dataperusahaan);
            dataalamatt = view.findViewById(R.id.dataalamat);
            dataemaill = view.findViewById(R.id.dataemail);
            datanohpp = view.findViewById(R.id.datanohp);
            usernamepro = view.findViewById(R.id.usernamepro);
            idclient=view.findViewById(R.id.idclient);
            User user = SharedPrefManager.getInstance(getContext()).getUser();

            dataperusahaann.setText(user.getNama_perusahaan());
            dataalamatt.setText(user.getAlamat());
            dataemaill.setText(user.getEmail());
            datanohpp.setText(user.getNo_hp());
            idclient.setText(String.valueOf(user.getId_client()));
            usernamepro.setText(user.getNama_client());
            logoutt.setOnClickListener(this);

        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(logoutt)) {
            SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
            getActivity().finish();
        }
    }

}