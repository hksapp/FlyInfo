package com.hkapps.flyinfo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Pranav on 26-03-2017.
 */

public class NotificationListener extends Service {

    private NotificationCompat.Builder mBuilder;
    private DatabaseReference lref1,lref2,lref3,dref,notifRef;
    private int notif_id = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {


        notifRef = FirebaseDatabase.getInstance().getReference().child("Flightstatus").child("status");

        notifRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String msg = dataSnapshot.getValue().toString();

                showNotifications("Flight Status",msg,2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        dref = FirebaseDatabase.getInstance().getReference().child("baggage");

        lref1 = dref.child("location1");
        lref2 = dref.child("location2");
        lref3 = dref.child("location3");

        lref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place =  dataSnapshot.child("place").getValue().toString();

                String time = dataSnapshot.child("time").getValue().toString();

                showNotifications(place ,"Baggage is at ",1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place =  dataSnapshot.child("place").getValue().toString();

                String time = dataSnapshot.child("time").getValue().toString();

                showNotifications(place ,"Baggage is at ",1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place =  dataSnapshot.child("place").getValue().toString();


                String time = dataSnapshot.child("time").getValue().toString();

                showNotifications(place ,"Baggage is at ",1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return START_STICKY;
    }

    private void showNotifications(String place,  String reacted , int type) {

        mBuilder = new NotificationCompat.Builder(this);


        // mBuilder.setLargeIcon(Picasso.with(getBaseContext()).load(pic).get());

        mBuilder.setContentTitle(place);
        mBuilder.setContentText(reacted + " " + place);
        mBuilder.setSmallIcon(R.drawable.common_google_signin_btn_icon_light_focused);

//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.circle_24dp);
  //      mBuilder.setLargeIcon(icon);
        //  Toast.makeText(this, pic, Toast.LENGTH_SHORT).show();
        // loadImage(getApplicationContext(), pic);

     /*   if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.bestfrnds);
        } else {
           mBuilder.setSmallIcon(R.drawable.bestfrnds);
        }*/





        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

if(type==2) {
    Intent resultIntent = new Intent(this, FlightStatusActivity.class);
    // resultIntent.putExtra("notif", true);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(FlightStatusActivity.class);

    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    // PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
    // PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

    mBuilder.setContentIntent(resultPendingIntent);
}else if(type==1){

    Intent resultIntent = new Intent(this, BaggageActivity.class);
    // resultIntent.putExtra("notif", true);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(BaggageActivity.class);


    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    // PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
    // PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

    mBuilder.setContentIntent(resultPendingIntent);

}

// Adds the Intent that starts the Activity to the top of the stack



        mBuilder.setVibrate(new long[]{500, 500});

       /* Notification note = mBuilder.build();
        note.defaults |= Notification.DEFAULT_VIBRATE;
        note.defaults |= Notification.DEFAULT_SOUND;
*/

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        mNotificationManager.notify(notif_id, mBuilder.build());

       /* final int notifId = 1337;
        final RemoteViews contentView = mBuilder.getContentView();
        final int iconId = android.R.id.icon;
        Picasso.with(getApplicationContext()).load(pic).into(contentView, iconId, notifId,mBuilder.build() );
*/



    }


}
