package br.edu.ifpb.appbuscarempregos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import br.edu.ifpb.appbuscarempregos.activity.MainActivity;

/**
 * Created by Henrique on 29/10/2016.
 */

public class SplashActivity extends Activity implements Runnable {

    private static int splashTime= 2000;
    private ImageView image;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = (ImageView) findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.load);

        AnimationDrawable load = (AnimationDrawable) image.getBackground();
        load.start();

        new Handler().postDelayed(this, splashTime);
    }


    @Override
    public void run() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }
}
