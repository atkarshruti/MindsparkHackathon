package com.example.tensorflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    TextView tv,pv;
    DatabaseReference databaseReference;
    dbHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=findViewById(R.id.textView2);
        pv=findViewById(R.id.textView3);

        Intent i=getIntent();
        String str=i.getStringExtra("message_key");
        tv.setText(str);

//        databaseReference= FirebaseDatabase.getInstance().getReference("solutions");
//        dbhandler=new dbHandler();
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dbHandler db=snapshot.getValue(dbHandler.class);
//                System.out.println(db);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        switch(str){
            case "Cabbage Black Rot":
                pv.setText("hello");
                break;
            default:
                pv.setText("No matches found.");
        }




    }
}