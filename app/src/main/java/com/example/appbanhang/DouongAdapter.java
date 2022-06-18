package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DouongAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<DoUong> doUongs;
    private  ArrayList<DoUong> doUongArrayList;
    public DouongAdapter(Context context, int layout, List<DoUong> doUongs) {
        this.context = context;
        this.layout = layout;
        this.doUongs = doUongs;
        this.doUongArrayList= new ArrayList<>();
        this.doUongArrayList.addAll(doUongs);
    }
    @Override
    public int getCount() {
        return doUongs.size();
    }

    @Override
    public Object getItem(int i) {
        return doUongs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Số dòng trên item. Lấy phần từ contexxt.
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        //Ánh xạ view.
        TextView txtTen=(TextView) view.findViewById(R.id.txtName);
        TextView txtMota=(TextView) view.findViewById(R.id.txtOverview);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.action_image);
        TextView giatien=(TextView) view.findViewById(R.id.giatien);
        //Gán giá trị.
        DoUong douong=doUongs.get(i);
        txtTen.setText(douong.getName());
        txtMota.setText(douong.getOverview());
        imgHinh.setImageResource(douong.getImage());
        giatien.setText(douong.getGiatien()+" Đ");
        return view;
    }

    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());

        doUongs.clear();
        if(charText.length()==0){
            doUongs.addAll(doUongArrayList);
        }
        else
        {
            for(DoUong doUong:doUongArrayList){
                if(doUong.name.toLowerCase(Locale.getDefault()).contains(charText)){
                    doUongs.add(doUong);
                }
            }
        }
        notifyDataSetChanged();
    }
}



