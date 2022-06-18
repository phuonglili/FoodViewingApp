package com.example.appbanhang;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class UpdateActivity extends AppCompatActivity {

    DataBase_Update database_update;
    ListView lvGhichu;
    ArrayList<GhiChuUpdate> arrGhiChu;
    UpdateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        lvGhichu = (ListView) findViewById(R.id.listghichu);
        arrGhiChu = new ArrayList<>();
        adapter = new UpdateAdapter(this, R.layout.dong_update, arrGhiChu);
        lvGhichu.setAdapter(adapter);

        database_update = new DataBase_Update(this, "ghichu.sqlite", null, 1);
        database_update.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenGC VARCHAR(255))");
        GetDataCongViec();
    }

    private void GetDataCongViec() {
        //Xóa mảng cập nhật dữ liệu mới
        Cursor dataGhichu = database_update.GetData("SELECT * FROM CongViec");
        //here
        arrGhiChu.clear();
        while (dataGhichu.moveToNext()) {
            String ten = dataGhichu.getString(1);
            int id = dataGhichu.getInt(0);
            arrGhiChu.add(new GhiChuUpdate(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addupdate);
        final EditText editThem = dialog.findViewById(R.id.editText);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(view -> {
            String tennd = editThem.getText().toString();
            if (tennd.equals("")) {
                Toast.makeText(UpdateActivity.this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
            } else {
                database_update.QueryData("INSERT INTO CongViec VALUES(null,'" + tennd + "')");
                dialog.dismiss();
                GetDataCongViec();
            }
        });
        dialog.show();
        btnHuy.setOnClickListener(view -> dialog.dismiss());
    }

    public void DialogXoaGC(String tengc, final int id) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa ghi chú " + tengc + " thật sự?");
        dialogXoa.setPositiveButton("Có", (dialogInterface, i) -> {
            database_update.QueryData("DELETE FROM CongViec WHERE Id='" + id + "'");
            dialog.dismiss();
            GetDataCongViec();
        });
        dialogXoa.setNegativeButton("Không", (dialogInterface, i) -> {
        });
        dialogXoa.show();
    }

    public void DialogSua(final int Id, String tenGC) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dong_edit);
        final EditText edt = (EditText) dialog.findViewById(R.id.edtSua);
        Button btnluu = (Button) dialog.findViewById(R.id.btnSave);
        edt.setText(tenGC);
        Button btnthoat = (Button) dialog.findViewById(R.id.btnCancel);
        btnluu.setOnClickListener(view -> {
            String tennd = edt.getText().toString();
            if (tennd.equals("")) {
                Toast.makeText(UpdateActivity.this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
            } else {
                database_update.QueryData("UPDATE CongViec SET TenGC = '" + tennd + "' WHERE Id = '" + Id + "' ");
                Toast.makeText(UpdateActivity.this, "Đã sửa ghi chú", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }
        });
        btnthoat.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }
}