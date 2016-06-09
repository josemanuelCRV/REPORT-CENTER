package com.labs.josemanuel.reportcenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.labs.josemanuel.reportcenter.ui.actividades.LoginActivity;
import com.labs.josemanuel.reportcenter.ui.actividades.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final TextView tv = (TextView) findViewById(R.id.welcomeLabel);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        /**
         * Acceso a SharedPreferences para realizar el redireccionamiento.
         * */
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_splash));
                    }
                }, 200);//Tiempo desde Gris hasta Amarillo

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        tv.setVisibility(View.VISIBLE);
                    }
                }, 200); //Tiempo Amarillo hasta Welcome

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent i;
                        if (sharedPreferences.getBoolean("login",true))
                            i = new Intent(SplashActivity.this, LoginActivity.class);
                        else
                        i = new Intent(SplashActivity.this, MainActivity.class);

                        startActivity(i);
                        finish();
                    }
                }, 200); //Tiempo Welcome hasta MainActivity


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}