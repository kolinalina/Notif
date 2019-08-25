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

public class AdapterData2 extends RecyclerView.Adapter<AdapterData2.HolderData> {
    private List<ModelData> mItems2 ;
    private Context context2;

    public AdapterData2(Context context2, List<ModelData> items2)
    {
        this.mItems2 = items2;
        this.context2 = context2;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row2 ,parent,false);
        HolderData holderData2 = new HolderData(layout);
        return holderData2;
    }

    @Override
    public void onBindViewHolder(HolderData holder2, int position) {
        ModelData mdd  = mItems2.get(position);
        holder2.tvidorder.setText(mdd.getId_order());
        holder2.tvidclient.setText(mdd.getId_client());
        holder2.tvidkomplain.setText(mdd.getId_komplain());
        holder2.tvdetailkomplain.setText(mdd.getDetail_komplain());

        holder2.mdd = mdd;


    }

    @Override
    public int getItemCount() {
        return mItems2.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvidorder,tvidclient,tvidkomplain,tvdetailkomplain;
        ModelData mdd;

        public  HolderData (View view2)
        {
            super(view2);

            tvidorder = view2.findViewById(R.id.idorderr);
            tvidclient =  view2.findViewById(R.id.idclientt);
            tvidkomplain =  view2.findViewById(R.id.idkomplainn);
            tvdetailkomplain =  view2.findViewById(R.id.detail);


            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context2, FormComplainActivity.class);
                    update.putExtra("update",1);
                    update.putExtra("id_order",mdd.getId_order());
                    update.putExtra("id_client",mdd.getId_client());
                    update.putExtra("id_komplain",mdd.getId_komplain());
                    update.putExtra("detail_komplain",mdd.getDetail_komplain());

                    context2.startActivity(update);
                }
            });
        }
    }
}
