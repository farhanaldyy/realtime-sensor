package com.example.iot_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    // inisialisasi textview
    private TextView humidity, temp, trash;

    // references firebase (koneksi host)
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // text view value
        humidity = (TextView)findViewById(R.id.humidity);
        temp = (TextView)findViewById(R.id.temp);
        trash = (TextView)findViewById(R.id.trash);

        // connect firebase
        mRef = new Firebase("https://cleany-app-9105a-default-rtdb.firebaseio.com/humidity");

        // process realtime database
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // value in child firebase
                String hum = dataSnapshot.getValue(String.class);
                // show in component value
                humidity.setText(hum);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}