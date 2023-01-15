package com.example.tensorflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class pre_list extends AppCompatActivity {


  //  DatabaseReference databaseReference;
    //    dbHandler dbhandler;
    TextView txt;
    //FirebaseDatabase firebaseDatabase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_list);

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("plant");
        txt = findViewById(R.id.text1);
//        Intent i = new Intent();
//        String str = i.getStringExtra("message_key");
//        txt.setText(str);
        // getData();


//        private void getData(){
//            databaseReference.addValueEventListener(new ValueEventListener() {
//
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    String value=dataSnapshot.getValue(String.class);
//                    txt.setText(value);
////                    for(DataSnapshot ds : dataSnapshot.getChildren()){
////
////
////                    }
//
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//            });
//
//
//
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(pre_list.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
    }
}
