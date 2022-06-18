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

public class BuaAnAdapter extends BaseAdapter {
    private final Context context;
    private final int layout;
    private final List<BuaAn> buaan;
    private final ArrayList<BuaAn> arrayList;

    public BuaAnAdapter(Context context, int layout, List<BuaAn> buaan) {
        this.context = context;
        this.layout = layout;
        this.buaan = buaan;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(buaan);
    }

    @Override
    public int getCount() {
        return buaan.size();
    }

    @Override
    public Object getItem(int i) {
        return buaan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Số dòng trên item. Lấy phần từ contexxt.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        //Ánh xạ view.
        TextView txtTen = view.findViewById(R.id.txtName);
        TextView txtMota = view.findViewById(R.id.txtOverview);
        ImageView imgHinh = view.findViewById(R.id.action_image);
        TextView giatien = view.findViewById(R.id.giatien);
        //Gán giá trị.
        BuaAn traicay = buaan.get(i);
        txtTen.setText(traicay.getName());
        txtMota.setText(traicay.getOverview());
        imgHinh.setImageResource(traicay.getImage());
        giatien.setText(traicay.getGiatien()+" Đ");

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        buaan.clear();
        if (charText.length() == 0) {
            buaan.addAll(arrayList);
        } else {
            for (BuaAn buaAn : arrayList) {
                if (buaAn.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    buaan.add(buaAn);
                }
            }
        }
        notifyDataSetChanged();
    }
}
