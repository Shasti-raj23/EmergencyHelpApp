package com.example.accident_detection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class chatbot extends Fragment {

    private RecyclerView chatsrv;
    private EditText userMSGedit;
    private FloatingActionButton sendMsgFAB;
    private final String BOT_key = "bot";
    private final String User_key = "user";
    private ArrayList<chatsModel> chatsModelArrayList;
    ChatRVAdapter chatRVAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public chatbot() {
        // Required empty public constructor
    }

    public static chatbot newInstance(String param1, String param2) {
        chatbot fragment = new chatbot();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatbot, container, false);

        chatsrv = view.findViewById(R.id.idRVChats);
        userMSGedit = view.findViewById(R.id.userentry);
        sendMsgFAB = view.findViewById(R.id.chatsend);
        chatsModelArrayList = new ArrayList<>();

        chatRVAdapter = new ChatRVAdapter(chatsModelArrayList, requireContext());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        chatsrv.setLayoutManager(manager);
        chatsrv.setAdapter(chatRVAdapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = userMSGedit.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(requireContext(), "Enter some message", Toast.LENGTH_SHORT).show();
                    return;
                }

                chatsModelArrayList.add(new chatsModel(message, User_key));
                chatRVAdapter.notifyDataSetChanged();

                handleBotResponses(message);

                userMSGedit.setText("");
            }
        });

        return view;
    }

    private void handleBotResponses(String message) {
        String response;
        if (message.equalsIgnoreCase("highway")) {
            response = "highway - 1033";
        } else if (message.equalsIgnoreCase("police")) {
            response = "police - 100";
        } else if (message.equalsIgnoreCase("ambulance")) {
            response = "ambulance - 108";
        } else if (message.equalsIgnoreCase("railway")) {
            response = "railway - 139";
        } else {
            response = "Sorry, I couldn't understand that";
        }

        chatsModelArrayList.add(new chatsModel(response, BOT_key));
        chatRVAdapter.notifyDataSetChanged();
    }
}
