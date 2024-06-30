package com.example.accident_detection;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;


import androidx.appcompat.app.AppCompatActivity;
import java.lang.String;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;


public class register extends AppCompatActivity {

    EditText pass, confpass, uname,blood,gname,gphone;
    EditText phone;
    Button sub;5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pass = findViewById(R.id.regpass);
        confpass = findViewById(R.id.conpass);
        uname = findViewById(R.id.uname);
        phone = findViewById(R.id.mobile_number);
        sub = findViewById(R.id.submit);
        blood = findViewById(R.id.bloodgroup);
        gname = findViewById(R.id.gardian_name);
        gphone = findViewById(R.id.gardian_phone);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = uname.getText().toString();
                String userMobile = phone.getText().toString();
                String userPass = pass.getText().toString();
                String userConPass = confpass.getText().toString();
                String userBlood = blood.getText().toString();
                String usergname = gname.getText().toString();
                String usergphone = gphone.getText().toString();
                Database db = new Database(getApplicationContext(), "Accident", null, 1);
                if (userName.length() == 0 || userConPass.length() == 0 || userMobile.length() == 0 || userPass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "fill all values", Toast.LENGTH_SHORT).show();
                } else {

                    if (userPass.compareTo(userConPass) == 0) {
                        if (isvalid(userConPass)) {
                            Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_SHORT).show();
                            db.register(userName,userPass,userMobile,userBlood,usergname,usergphone);
                            startActivity(new Intent(register.this, loginpage.class));

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Confirm pass and pass didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public static boolean isvalid(String pa){
        int f0=0,f1=0,f2=0;
        if(pa.length()<8){
//            return false;
            return true;

        }
        else {
            for(int i=0;i<pa.length();i++){
                char a= pa.charAt(i);
                if(Character.isDigit(a)){
                    f0=1;
                } else if (Character.isAlphabetic(a)) {
                    f1=1;
                } else if ((a>=33&&a<=46)||a==64) {
                    f2=1;
                }
            }
            if(f0==1||f1==1||f2==1||pa.charAt(0)=='a'){
                return true;
            }
            else{
                return false;
            }
        }


    }


}

