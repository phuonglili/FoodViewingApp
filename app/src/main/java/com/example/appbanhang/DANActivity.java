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

public class DANActivity extends Activity implements AdapterView.OnItemClickListener  {
        ListView listView;
        public static ArrayList<BuaAn> arrTraicay;
        BuaAnAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_doan);
            listView =  findViewById(R.id.lvBuaan);

            arrTraicay = new ArrayList<>();
            arrTraicay.add(new BuaAn("Bún Chả", "Bún,chả thịt lợn nướng trên than hoa và bát nước mắn chua cay mặn  ngọt ", R.drawable.buncha,30000));
            arrTraicay.add(new BuaAn("Bún Đậu Mắm Tôm ", "Bún tươi,Đậu chiên vàng ,chả cốm,nem chua,mắm tôm pha chanh,ớt và ăn kèm với các loại rau thơm như tía tô ,kinh giới,...", R.drawable.bundau,35000));
            arrTraicay.add(new BuaAn("CƠm Trộn Hàn Quốc", "Món Cơm trộn độc đáo này được làm từ các nguyên liệu như thịt,rau củ và trứng", R.drawable.comtron,20000));
            arrTraicay.add(new BuaAn("Cơm Rang", "Ngon Chuẩn vị của người Hà Thành với hạt cơm rang vàng ươm được bao bọc bởi trứng gà kết hợp với thị bò và chút dưa muối chu khiến cho món ngon hơn", R.drawable.comrang,30000));
            arrTraicay.add(new BuaAn("Bánh xèo", "Nhân Bánh xèo thường được làm từ thịt ba chỉ,tôm,đậu xanh", R.drawable.banhxeo,25000));
            arrTraicay.add(new BuaAn("Chả Tôm", "Chả tôm được làm từ nguyên liệu chính là tôm tươi rất ngon giàu chất dinh dưỡng và khoáng chất", R.drawable.chatom,35000));
            arrTraicay.add(new BuaAn("Xôi lạc ", "Dừa, khoai, cà chua, thịt, tàu", R.drawable.xoi,10000));
            arrTraicay.add(new BuaAn("Bánh Bao", "Bánh Bao được chế biến từ bột , rồi có thịt , có trứng và trải qua quá trình công phu", R.drawable.banhbao,12000));
            arrTraicay.add(new BuaAn("Nem nướng", "Dưa, măng, cá, thịt, trứng", R.drawable.nemnuong,30000));
            arrTraicay.add(new BuaAn("Cháo gà", "Gạo nếp, thịt gà băm, hành lá", R.drawable.chaoga,25000));
            arrTraicay.add(new BuaAn("Bánh bao", "Bột nở, thịt lợn, mộc nhĩ nấm hương, trứng chim cút", R.drawable.banhbao,12000));
            arrTraicay.add(new BuaAn("Bánh mì pate", "Bánh mì, pate, dưa chuột, rau thơm, trứng chiên", R.drawable.banhmipate,15000));
            arrTraicay.add(new BuaAn("Gà chiên", "Gà tươi được phủ lớp chiên giòn và đi kèm nước chấm tương ớt", R.drawable.gachien,100000));
            arrTraicay.add(new BuaAn("Bánh đa cua", "Bánh đa cua lẫn trong màu xanh mướt của rau muống đi kèm là màu vàng của chả cá, hành khô, gạch cua, tôm lột vỏ, màu đỏ của cà chua", R.drawable.banhdacua,40000));
            adapter = new BuaAnAdapter(this, R.layout.list_monan, arrTraicay);
            listView.setAdapter(adapter);

            //Bắt sự kiện click
            listView.setOnItemClickListener(this);
            EditText edtSearch;
            edtSearch = findViewById(R.id.edtSearch);

            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    adapter.filter(charSequence.toString().trim());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        @Override
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

    //@Override
        //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //   Intent intent=new Intent(this,ViewThucdonActivity.class);
        //   startActivity(intent);
        //}

    }


