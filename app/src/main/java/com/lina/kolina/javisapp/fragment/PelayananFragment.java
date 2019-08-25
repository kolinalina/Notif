package com.lina.kolina.javisapp.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.lina.kolina.javisapp.AdapterData;
import com.lina.kolina.javisapp.AdapterData2;
import com.lina.kolina.javisapp.DeleteOrderActivity;
import com.lina.kolina.javisapp.FormComplainActivity;
import com.lina.kolina.javisapp.FormOrderActivity;
import com.lina.kolina.javisapp.LoginActivity;
import com.lina.kolina.javisapp.MainActivity;
import com.lina.kolina.javisapp.ModelData;
import com.lina.kolina.javisapp.R;
import com.lina.kolina.javisapp.SharedPrefManager;
import com.lina.kolina.javisapp.URLs;
import com.lina.kolina.javisapp.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PelayananFragment extends Fragment implements View.OnClickListener {

    ImageView orderr, complainn;
    RecyclerView mRecyclerview,mRecyclerview2;
    RecyclerView.Adapter mAdapter, mAdapter2;
    RecyclerView.LayoutManager mManager, mManager2;
    List<ModelData> mItems, mItems2;
    Button btnDelete;
    ProgressDialog pd;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pelayanan, container, false);

            orderr = view.findViewById(R.id.order);
            complainn = view.findViewById(R.id.complain);
//            mRecyclerview = view.findViewById(R.id.recyclerviewTemp);
//        mRecyclerview2 = view.findViewById(R.id.recyclerviewTempp);
//
//            btnDelete = view.findViewById(R.id.btn_delete);
//            pd = new ProgressDialog(getActivity());
//            mItems = new ArrayList<>();
//
//
//            loadJson();
//
//
//
//            mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//            mRecyclerview.setLayoutManager(mManager);
//            mAdapter = new AdapterData(getActivity(), mItems);
//            mRecyclerview.setAdapter(mAdapter);
//
//        mItems2 = new ArrayList<>();
//
//        mManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        mRecyclerview2.setLayoutManager(mManager2);
//        mAdapter2 = new AdapterData(getActivity(), mItems2);
//        mRecyclerview2.setAdapter(mAdapter2);
//
//        loadJson2();
//

            orderr.setOnClickListener(this);
            complainn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), FormComplainActivity.class);
                    startActivity(intent);

                }
            });
//            btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), DeleteOrderActivity.class);
//                    startActivity(intent);
//                }
//            });

        return view;
        }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FormOrderActivity.class);
        startActivity(intent);
    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, URLs.URL_DATA,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setId_client(data.getString("id_client"));
                                md.setId_order(data.getString("id_order"));
                                md.setNama_proyek(data.getString("nama_proyek"));
                                md.setLokasi_proyek(data.getString("lokasi_proyek"));
                                md.setDeskripsi_proyek(data.getString("deskripsi_proyek"));
                                md.setDeadline(data.getString("deadline"));
                                md.setStatus(data.getString("status"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(reqData);
    }

    private void loadJson2()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData2 = new JsonArrayRequest(Request.Method.POST, URLs.URL_DATA2,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject dataa = response.getJSONObject(i);
                                ModelData mdd = new ModelData();
                                mdd.setId_client(dataa.getString("id_client"));
                                mdd.setId_order(dataa.getString("id_order"));
                                mdd.setId_komplain(dataa.getString("id_komplain"));
                                mdd.setDetail_komplain(dataa.getString("detail_komplain"));

                                mItems2.add(mdd);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter2.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(reqData2);
    }

}

