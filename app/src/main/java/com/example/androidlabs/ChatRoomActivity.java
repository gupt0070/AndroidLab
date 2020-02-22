package com.example.androidlabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MessageModel> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        db = new DatabaseHelper(this);
        ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());

        listMessage.addAll(db.read());
        listView.setAdapter(adt);
        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();

            MessageModel model = new MessageModel(message, true);
            listMessage.add(model);
            editText.setText("");

//          ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext())
            db.create(model);

            listView.setAdapter(adt);
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
               MessageModel model = new MessageModel(message, false);
                listMessage.add(model);
               editText.setText("");
//                ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
               db.create(model);
               listView.setAdapter(adt);

        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setTitle(getString(R.string.delete_confirm_msg))
                    .setMessage(getString(R.string.the_selected_row_is) + " " + position + "\n" +
                            getString(R.string.the_database_id_is) + " " + id)
                    .setPositiveButton(R.string.yes, (DialogInterface dialog, int which) -> {
                        listMessage.remove(position);
                        ChatAdapter myAdapter = new ChatAdapter(listMessage, getApplicationContext());
                        listView.setAdapter(myAdapter);
                        //   ChatAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
            return true;
        });

        Log.d("ChatRoomActivity","onCreate");

    }


}

