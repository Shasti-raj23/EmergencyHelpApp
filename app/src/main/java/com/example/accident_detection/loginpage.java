package com.example.accident_detection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginpage extends AppCompatActivity {// Define a unique request code
    Button login;
    TextView reg;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.new1);
        user = findViewById(R.id.name);
        pass = findViewById(R.id.pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getText().toString();
                String userPass = pass.getText().toString();
                Database db = new Database(getApplicationContext(), "Accident", null, 1);
                if (userName.length() == 0 || userPass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "fill details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(userName, userPass)) {
                        Toast.makeText(getApplicationContext(), "logined", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginpage.this, Navigate.class);
                        intent.putExtra("USERNAME_EXTRA", userName); // Pass the username as an extra
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginpage.this, register.class));
            }
        });

    }




}
