package com.example.appbanhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText edt_username, edt_pass;
    LinearLayout login;
    ConstraintLayout register;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();

        AnhXa();
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    private void AnhXa() {
        edt_pass = findViewById(R.id.edtPass);
        edt_username = findViewById(R.id.edtMail);
        login = findViewById(R.id.tvLogin);
        register = findViewById(R.id.layout_register);
        //Xét sự kiện khi text trong EditText email thay đổi
        edt_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        // Khi text trong EditText thay đổi sẽ thực hiện hành động trong block này
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!emailValid()) {
                    edt_username.setError("Sai định dạng");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //xét sự kiện khi text trong EditText thay đổi
        edt_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        //Khi text trong EdditText thay đổi sẽ thực hiện trong block này
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
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvLogin) {
            if (checkValid()) {
                String mail = edt_username.getText().toString();
                String pass = edt_pass.getText().toString();
                login(mail, pass);
            }
        }
        if (id == R.id.layout_register) {
           startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    void login(String mail, String pass) {
        Intent intent = new Intent(this, MainActivity.class);

        // đăng nhập
        firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // thành công thì vào màn home
                if (task.isSuccessful()) {
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Authentication success.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //thất bại
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    // Kiểm tra định dạng email
    public Boolean emailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(edt_username.getText().toString()).matches();
    }

    // Kiểm tra định dạng Pass
    public Boolean passValid() {
        return edt_pass.getText().toString().length() > 5;
    }

    // Kiểm tra định dạng của cả mail và pass
    public Boolean checkValid() {
        return emailValid() && passValid();
    }

}
