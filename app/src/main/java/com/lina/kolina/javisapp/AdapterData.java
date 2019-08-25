package com.lina.kolina.javisapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static java.security.AccessController.getContext;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;

    public AdapterData (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row ,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md  = mItems.get(position);
        holder.tvidorder.setText(md.getId_order());
        holder.tvidclient.setText(md.getId_client());
        holder.tvnamaproyek.setText(md.getNama_proyek());
        holder.tvlokasiproyek.setText(md.getLokasi_proyek());
//        holder.tvdesproyek.setText(md.getDeskripsi_proyek());
        holder.tvstatus.setText(md.getStatus());
        holder.tvdeadline.setText(md.getDeadline());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvidorder,tvidclient,tvnamaproyek,tvlokasiproyek,tvdesproyek,tvstatus,tvdeadline;
        ModelData md;

        public  HolderData (View view)
        {
            super(view);

            tvidorder = view.findViewById(R.id.idorder);
            tvidclient =  view.findViewById(R.id.idclient);
            tvnamaproyek =  view.findViewById(R.id.proyek);
            tvlokasiproyek =  view.findViewById(R.id.lokasi);
            tvstatus =  view.findViewById(R.id.status);
            tvdeadline =  view.findViewById(R.id.deadline);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, FormOrderActivity.class);
                    update.putExtra("update",1);
                    update.putExtra("id_order",md.getId_order());
                    update.putExtra("id_client",md.getId_client());
                    update.putExtra("nama_proyek",md.getNama_proyek());
                    update.putExtra("lokasi_proyek",md.getLokasi_proyek());
                    update.putExtra("deskripsi_proyek",md.getDeskripsi_proyek());
                    update.putExtra("deadline",md.getDeadline());

                    context.startActivity(update);
                }
            });
        }
    }
}
