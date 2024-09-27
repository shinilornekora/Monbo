package com.example.onboardactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity);
        Log.d("Home", "Let-me-create-pls");
    }

    override fun onStart() {
        super.onStart()
        Log.d("Home", "Let-me-start-pls");
    }

    override fun onResume() {
        super.onResume()
        Log.d("Home", "Let-me-resume-pls");
    }

    override fun onStop() {
        super.onStop();
        Log.d("Home", "Let-me-stop-pls");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Home", "Let-me-destroy-pls");
    }
}