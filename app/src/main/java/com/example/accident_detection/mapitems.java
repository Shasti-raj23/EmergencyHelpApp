package com.example.accident_detection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class mapitems extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PERMISSION_REQUEST_CODE = 2;

    private String mParam1;
    private String mParam2;

    public mapitems() {
        // Required empty public constructor
    }

    public static mapitems newInstance(String param1, String param2) {
        mapitems fragment = new mapitems();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    CardView police, hos, bus;
    Button btn ;
    EditText medi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapitems, container, false);

        police = view.findViewById(R.id.police);
        hos = view.findViewById(R.id.hospital);
        bus = view.findViewById(R.id.bus);
        btn = view.findViewById(R.id.sendbtn);
        medi = view.findViewById(R.id.medit);
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), lauchapp.class);
                it.putExtra("type", "police");
                startActivity(it);
            }
        });

        hos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), lauchapp.class);
                it.putExtra("type", "hospital");
                startActivity(it);
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), lauchapp.class);
                it.putExtra("type", "bus station");
                startActivity(it);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

                }


            }
        });

        return view;
        
    


    }

    private void sendSMS() {
        String phone = "8667531781";
        String sms = "this is a test message";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            Toast.makeText(getContext(), "the message is sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),"message not send",Toast.LENGTH_SHORT).show();
        }
    }
}
