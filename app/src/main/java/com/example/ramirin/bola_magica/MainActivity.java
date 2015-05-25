package com.example.ramirin.bola_magica;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
//import java.util.Calendar;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    public void onClick(View v) {
        try {
            sonido();
        } catch (IOException e) {
            e.printStackTrace();
        }

        vibrar(1000);
        showToastMessage(respuestas(),1000);
    }

    public  void sonido() throws IOException {
        MediaPlayer mp;
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.reset();
                mp.release();
                mp=null;
            }

        });
        mp.start();
    }




    public void vibrar(int time)
    {
        //get instance vibrator from Context
        Vibrator v =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Vibrate for 400 milliseconds
        v.vibrate(time);
    }

    public String respuestas()
      {
          //Calendar c= Calendar.getInstance();
         // long seconds= c.get(Calendar.SECOND);
          //Random aleatorio = new Random(seconds);
          Random aleatorio = new Random();
          int a= aleatorio.nextInt(19);

      String[] respuestas = getResources().getStringArray(R.array.respuestas);
      return respuestas[a];
      }

    public void showToastMessage(String text, int duration) {
        final Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        //final Toast toast = Toast.makeText(this, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
