package com.example.accident_detection;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigate extends AppCompatActivity {
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        user = getIntent().getStringExtra("USERNAME_EXTRA");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                new homepage()).commit();
        System.out.println(user);

        // Display the default fragment


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if(item.getItemId()==R.id.Home)
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                new homepage()).commit();
                    else if(item.getItemId()==R.id.Location)
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                new mapitems()).commit();
                    else if(item.getItemId()==R.id.Chatbot)
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                new chatbot()).commit();
                    else if(item.getItemId()==R.id.Profile) {
                        profilepage fragment = new profilepage();
                        Bundle bundle = new Bundle();
                        bundle.putString("USERNAME_EXTRA", user);
                        fragment.setArguments(bundle);
//                        profilepage fragment = profilepage.newInstance(user, null);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();


                    }
                    return true;
                }
    };

}