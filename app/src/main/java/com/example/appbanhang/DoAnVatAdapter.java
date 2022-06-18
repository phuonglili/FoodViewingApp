package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

public class DoAnVatAdapter extends BaseAdapter {
    private final Context context;
    private final int layout;
    private final List<DoAnVat> doAnVatList;
    private final ArrayList<DoAnVat> doAnVatArrayList;
    public DoAnVatAdapter(Context context, int layout, List<DoAnVat> doAnVatList) {
        this.context = context;
        this.layout = layout;
        this.doAnVatList = doAnVatList;
        this.doAnVatArrayList= new ArrayList<>();
        this.doAnVatArrayList.addAll(doAnVatList);
    }
    @Override
    public int getCount() {
        return doAnVatList.size();
    }

    @Override
    public Object getItem(int i) {
        return doAnVatArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        //Ánh xạ view.
        TextView txtTen=(TextView) view.findViewById(R.id.txtName);
        TextView txtMota=(TextView) view.findViewById(R.id.txtOverview);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.action_image);
        TextView giatien=(TextView) view.findViewById(R.id.giatien);
        //Gán giá trị.
        DoAnVat doAnVat=doAnVatList.get(i);
        txtTen.setText(doAnVat.getName());
        txtMota.setText(doAnVat.getOverview());
        imgHinh.setImageResource(doAnVat.getImage());
        giatien.setText(doAnVat.getGiatien()+" Đ");
        return view;
    }
    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());

        doAnVatList.clear();
        if(charText.length()==0){
            doAnVatList.addAll(doAnVatArrayList);
        }
        else
        {
            for(DoAnVat doAnVat :doAnVatArrayList){
                if(doAnVat.name.toLowerCase(Locale.getDefault()).contains(charText)){
                    doAnVatList.add(doAnVat);
                }
            }
        }
        notifyDataSetChanged();
    }
}
