package com.example.accident_detection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class profilepage extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public profilepage() {
        // Required empty public constructor
    }

    public static profilepage newInstance(String param1, String param2) {
        profilepage fragment = new profilepage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView name, phone, blood, gphone;
    Button map, home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilepage, container, false);

        name = view.findViewById(R.id.p_name);
        phone = view.findViewById(R.id.p_phone);
        blood = view.findViewById(R.id.p_blood);
        gphone = view.findViewById(R.id.p_gphone);
        Bundle args = getArguments();
        if (args != null) {
            String username = args.getString("USERNAME_EXTRA");
            Database db = new Database(requireContext(), "Accident", null, 1);
            String[] data = db.get_user_info(username);
            if (data != null && data.length == 4) {
                name.setText(data[0]);
                phone.setText(data[1]);
                blood.setText(data[2]);
                gphone.setText(data[3]);
            }
        }

        return view;
    }
}
