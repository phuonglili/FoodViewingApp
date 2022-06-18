package com.example.appbanhang;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class DAVActivity extends Activity implements AdapterView.OnItemClickListener {
        ListView lstanvat;
        public static ArrayList<DoAnVat> anVatArrayList;
        DoAnVatAdapter adapterdav;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_doanvat);
            lstanvat=findViewById(R.id.listdoanvat);
            anVatArrayList = new ArrayList<>();
            anVatArrayList.add(new DoAnVat("Khoai tây chiên ", "Khoai tây chiên là món ăn vặt được rất nhiều người yêu thích dù là trẻ con hay người lớn,khoai tây giòn giòn thơm ngậy ", R.drawable.khoaitaychien,200000));
            anVatArrayList.add(new DoAnVat("Kim bap chiên  ", "Được làm với nguyên liệu chính là lá rong biển,trứng,xúc xích,cơm,cà rốt kết hợp với nước chấm cay  ", R.drawable.kimbap,35000));
            anVatArrayList.add(new DoAnVat("Cá viên chiên ", "Thành phần gồm cá xay kết hợp với rau củ  ", R.drawable.cavien,25000));
            anVatArrayList.add(new DoAnVat("Khoai lang kén ", " Thành phần làm từ khoai lang nhào với bột năng được chiên giòn thơm ngậy  ", R.drawable.khoailangken,28000));
            anVatArrayList.add(new DoAnVat("Hamburger ", "Bánh mỳ kẹp phomai,xúc xích và tôm ", R.drawable.hamburger,350000));
            anVatArrayList.add(new DoAnVat("Xúc xích ", " Được làm từ gan lợn sạch,qua chế biến và rán giòn", R.drawable.xucxich,10000));
            anVatArrayList.add(new DoAnVat("Bánh gà ", "Được làm từ ức gà,chiên giòn với nhân phô mai bên trong ", R.drawable.banhga,20000));
            anVatArrayList.add(new DoAnVat("Khoai môn lệ phố", "Được làm từ khoai lang kết hợp với nhân khoai môn ", R.drawable.khoaimon,20000));
            anVatArrayList.add(new DoAnVat("Nem chua rán ", "Với thành phần nem tươi, được phủ bởi 1 lớp bột chiên giòn khi rán,thơm ngon béo ngậy ", R.drawable.nemchua,25000));
            anVatArrayList.add(new DoAnVat("Bánh tráng trộn ", "Món ăn được nhiều người ưa thích,có nhiều toping khác nhau như trứng cút,khô bò,..  ", R.drawable.banhtrangtron,30000));

            adapterdav = new DoAnVatAdapter(this,R.layout.list_monan,anVatArrayList);
            lstanvat.setAdapter(adapterdav);
            //Bắt sự kiện click
            lstanvat.setOnItemClickListener(this);
            EditText edtSearch;
            edtSearch = findViewById(R.id.edtSearchDAV);

            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    adapterdav.filter(charSequence.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Intent intent=new Intent(this,ViewMonanddActivity.class);
            //startActivity(intent);
        }
        public boolean onCreateOptionsMenu(Menu menu) {
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return super.onOptionsItemSelected(item);
        }
    }


