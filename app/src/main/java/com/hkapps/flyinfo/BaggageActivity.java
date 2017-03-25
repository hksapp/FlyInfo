package com.hkapps.flyinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaggageActivity extends AppCompatActivity {
    private TextView time1 ,time2, time3 , place1, place2 , place3 ;
    private DatabaseReference dref ,lref1 , lref2 , lref3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baggage);


        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        time3 = (TextView)findViewById(R.id.time3);
        place1 = (TextView)findViewById(R.id.place1);
        place2 = (TextView)findViewById(R.id.place2);
        place3 = (TextView)findViewById(R.id.place3);




        dref = FirebaseDatabase.getInstance().getReference().child("baggage");

        lref1 = dref.child("location1");
        lref2 = dref.child("location2");
        lref3 = dref.child("location3");

        lref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

             String place =  dataSnapshot.child("place").getValue().toString();
                place1.setText(place);

                String time = dataSnapshot.child("time").getValue().toString();
                time1.setText(time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place =  dataSnapshot.child("place").getValue().toString();
                place2.setText(place);

                String time = dataSnapshot.child("time").getValue().toString();
                time2.setText(time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place =  dataSnapshot.child("place").getValue().toString();
                place3.setText(place);

                String time = dataSnapshot.child("time").getValue().toString();
                time3.setText(time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
