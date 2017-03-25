package com.hkapps.flyinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BaggageActivity extends AppCompatActivity {
    private TextView time1 ,time2, time3 , place1, place2 , place3 ;

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

    }
}
