package com.example.appbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_da, btn_du, btn_dav, btn_tt, btn_tv, btn_cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        btn_da.setOnClickListener(this);
        btn_du.setOnClickListener(this);
        btn_cn.setOnClickListener(this);
        btn_tv.setOnClickListener(this);
        btn_dav.setOnClickListener(this);
        btn_tt.setOnClickListener(this);
    }

    private void Anhxa() {
        btn_da = findViewById(R.id.btndoan);
        btn_du = findViewById(R.id.btndouong);
        btn_dav = findViewById(R.id.btndoanvat);
        btn_tt = findViewById(R.id.btnTintuc);
        btn_tv = findViewById(R.id.btnTuvan);
        //btn_qn = findViewById(R.id.btnQuanan);
        btn_cn = findViewById(R.id.btnCapnhat);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btndoan) {
            //Menu tìm kiếm: theo đồ ăn
            Intent intent = new Intent(this, DANActivity.class);
            startActivity(intent);
        }

        if (id == R.id.btndouong) {
            //Gọi tới activity list view đồ uống
            Intent intent = new Intent(this, DoUongActivity.class);
            startActivity(intent);
        }

        if (id == R.id.btndoanvat) {
            Intent intent = new Intent(this, DAVActivity.class);
            startActivity(intent);
        }

        if (id == R.id.btnTintuc) {
            //Gọi tơi 1 activity tin tức: Đó là load trang Vnexpress đã làm( về chủ để sức khỏe. Phải có kết nói internet
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vnexpress.net/suc-khoe"));
            startActivity(intent);

            Log.d("TAG", "onClick: xx");
        }

        //Tư vấn người dùng
        if (id == R.id.btnTuvan) {
            if (isPermissionGranted()) {

                callAction();
            }
        }

        if (id == R.id.btnCapnhat) {
            //Gọi tới hàm cập nhật (Ghi chú)
            Intent intent = new Intent(this, UpdateActivity.class);
            startActivity(intent);
        }
    }

    public void callAction() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0919594461"));
        startActivity(callIntent);
    }
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    callAction();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
