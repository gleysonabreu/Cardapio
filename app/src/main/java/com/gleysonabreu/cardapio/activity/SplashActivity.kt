package com.gleysonabreu.cardapio.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gleysonabreu.cardapio.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            openActivity();

        }, 2000)
    }

    private fun openActivity(){

        val i: Intent = Intent(this, MainActivity::class.java);
        startActivity(i);
        finish();

    }
}
