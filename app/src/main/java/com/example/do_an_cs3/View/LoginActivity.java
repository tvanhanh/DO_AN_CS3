package com.example.do_an_cs3.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.do_an_cs3.Database.Database;
import com.example.do_an_cs3.Database.DatabaseManager;
import com.example.do_an_cs3.R;

import java.sql.PreparedStatement;

public class LoginActivity extends AppCompatActivity {
    private Button LoginButton;
    private Button RegisterButton;
    private DatabaseManager dbManager;

    private EditText loginTextEmail;
    private EditText loginTextPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbManager = new DatabaseManager(this);
        loginTextEmail = findViewById(R.id.loginEditTextEmail);
        loginTextPassword = findViewById(R.id.loginEditTextPassword);

        LoginButton = findViewById(R.id.btnLogin);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               handleLogin();
            }
        });

        RegisterButton = findViewById(R.id.btnRegisterNew);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void handleLogin(){
        String email = loginTextEmail.getText().toString();
        String password = loginTextPassword.getText().toString();

        if (dbManager.login(email, password, LoginActivity.this)) {
            // Nếu đăng nhập thành công, không cần phải làm gì thêm ở đây
        } else {
            // Nếu đăng nhập thất bại, hiển thị thông báo lỗi hoặc thực hiện các hành động khác
            Toast.makeText(LoginActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
        }
    }
    }
