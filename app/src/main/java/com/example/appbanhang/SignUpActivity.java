package com.example.appbanhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends Activity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    EditText edt_mail, edt_pass, edt_pass_again;
    LinearLayout register;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        //khởi tạo firebase
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();

        AnhXa();
        back.setOnClickListener(this);
        register.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        // đăng ký
        if (id == R.id.cvRegister) {
            if (checkValid()){
                String mail = edt_mail.getText().toString();
                String pass = edt_pass.getText().toString();
                register(mail, pass);
            }

        }
        //back màn hình
        if (id == R.id.ivBack) {
            onBackPressed();
        }
    }

    void register(String mail, String pass) {

        // đăng nhập
        firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // thành công
                if (task.isSuccessful()) {
                    onBackPressed();
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Thất bại thì quay trở lại màn login
                    onBackPressed();
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void AnhXa() {
        edt_mail = findViewById(R.id.edtMail);
        edt_pass = findViewById(R.id.edtPass);
        edt_pass_again = findViewById(R.id.edtPassAgain);
        back = findViewById(R.id.ivBack);
        register = findViewById(R.id.cvRegister);
        // xét sự kiện khi text trong EditText email thay đổi
        edt_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        // Khi text trong EditText thay đổi sẽ thực hiện trong khối này
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!emailValid()) {
                    edt_mail.setError("Sai định dạng");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // xét sự kiện khi text trong EditText mật khẩu thay đổi
        edt_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        // khi text trong EditText thay đổi sẽ thực hiện hành động trong khối này
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!passValid()) {
                    edt_pass.setError("Sai định dạng");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // xét sự kiện text trong EditText yêu cầu nhập lại mật khẩu vừa nhập
        edt_pass_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        // khi text trong EditText thay đổi sẽ thực hiện hành động trong khối này
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!passAgainValid()) {
                    edt_pass_again.setError("Mật khẩu phải giống nhau");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // Kiểm tra định dạng email
    public Boolean emailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(edt_mail.getText().toString()).matches();
    }

    // Kiểm tra định dạng Pass
    public Boolean passValid() {
        return edt_pass.getText().toString().length() > 5;
    }

    // kiểm tra 2 pass gióng nhau không
    public Boolean passAgainValid() {
        return edt_pass_again.getText().toString().equals(edt_pass.getText().toString());
    }

    // Kiểm tra định dạng của cả mail và pass
    public Boolean checkValid() {
        return emailValid() && passValid() && passAgainValid();
    }

}

