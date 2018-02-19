package com.example.lap_lenovo.timernotificacion;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    Button btn;
    Timer t;
    TimerTask tt;
    int tiempo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=(RadioGroup) findViewById(R.id.radioGroup);
        btn=(Button) findViewById(R.id.button);

        TextView tv;
        tv=(TextView)findViewById(R.id.textView);
        t= new Timer();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(rg.getCheckedRadioButtonId()){
                    case R.id.r1:
                        tiempo=5;
                        break;
                    case R.id.r2:
                        tiempo=15;
                        break;
                    case R.id.r3:
                        tiempo=30;
                        break;
                    case R.id.r4:
                        tiempo=60;
                        break;
                    default:
                        tiempo=0;
                }crearTimer();
            }
        });
    }

    public void crearTimer(){
        tt=new TimerTask() {
            @Override
            public void run() {
                if(tiempo>0){

                    tiempo--;
                    if(tiempo==0){
                        lanzarNotificacion();

                    }
                }
            }
        };
        t.schedule(tt,1000);
    }

    public void lanzarNotificacion(){
        NotificationCompat.Builder noti1;
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        noti1=new NotificationCompat.Builder(this);
        noti1.setSmallIcon(R.mipmap.ic_launcher);
        noti1.setTicker("Notificacion");
        noti1.setContentTitle("Notificacion de tiempo");
        noti1.setContentText("Mensaje de notificacion");
        noti1.setPriority(NotificationCompat.PRIORITY_HIGH);
        noti1.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=
                PendingIntent.getActivity(MainActivity.this,0,intent,0);

        noti1.setContentIntent(pendingIntent);
        manager.notify(10,noti1.build());
       t.cancel();
    }

}
