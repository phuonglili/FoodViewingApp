package com.example.appbanhang;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



import android.widget.ListView;

import java.util.ArrayList;
public class DoUongActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView listViewduong;
    public static ArrayList<DoUong> arrdouong;
    DouongAdapter douongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duong);
        listViewduong = findViewById(R.id.lvdouong);

        arrdouong = new ArrayList<>();
        arrdouong.add(new DoUong("Nước ép Dưa Hấu ", "Nước ép dưa hấu là một thức uống giải khát phổ biến và được nhiều người ưu chuộng ", R.drawable.nuocepduahau, 15000));
        arrdouong.add(new DoUong("Sinh Tố Xoài  ", "Sinh Tố Xoài gồm xoài đường , sữa chua, sữa tươi và đá ", R.drawable.sinhtoxoai, 25000));
        arrdouong.add(new DoUong("Capuchino ", "tên gọi của 1 loại cafe nóng với 3 thành phần chính là sữa nóng + cà phê Espesso +sữa sủi bọt và bột cacao   ", R.drawable.capuchino, 40000));
        arrdouong.add(new DoUong("Nước ép ổi ", "Nước ép ổi rất giàu vitamin A,vitamin C và các chất chống oxy hóa như carotone giúp bảo vệ da khỏi các nếp nhăn  ", R.drawable.nuocepoi, 20000));
        arrdouong.add(new DoUong("Nước ép Cà rốt ", "Nước ép Cà rốt được chiết xuất từ cà rốt nguyên hạt và rất bổ dưỡng . Nó không chỉ cung cấp Kali và vitamin C và giàu vitamin A ", R.drawable.nuocepcarot, 25000));
        arrdouong.add(new DoUong("Trà Vải Thanh Long ", "VÍ dụ tiêu biểu cho sự kết hợp hoàn hảo giữa vị chát nhẹ của trà, vị ngọt thanh của vải và thanh long  ", R.drawable.travai, 25000));
        arrdouong.add(new DoUong("Nâu Đá ", "Nâu Đá kết hợp tinh tế giữa vị ngọt béo ngậy của sữa đặc , với vị thơm ngọt đắng của cà phê ", R.drawable.nauda, 53000));
        arrdouong.add(new DoUong("Bạc xỉu", "Bạc xỉu thơm lừng của vị sữa đặc ,chút đắng của cà phê tạo nên hương vị của Sài GÒn của những ngày xưa ", R.drawable.bacxiu, 51000));
        arrdouong.add(new DoUong("Cà Phê  cốt dừa ", "Cà Phê cốt dừa  là phiên bản đồ uống biến tấu đầy mới lạ . Cà phê kết hợp với vị cốt dừa béo ngậy  ", R.drawable.caphecotdua, 35000));
        arrdouong.add(new DoUong("Nước dừa ", "Khi bổ ra bên trong là nước dừa rất ngọt và mát, lớp cơm dừa màu trắng tinh, ăn có vị béo  ", R.drawable.nuocdua, 25000));

        douongAdapter = new DouongAdapter(this, R.layout.list_monan, arrdouong);
        listViewduong.setAdapter(douongAdapter);
        //Bắt sự kiện click
        listViewduong.setOnItemClickListener(this);
        EditText edtSearch;
        edtSearch = findViewById(R.id.edtSearchDU);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                douongAdapter.filter(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
