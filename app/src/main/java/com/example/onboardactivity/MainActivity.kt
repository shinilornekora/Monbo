package com.example.onboardactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Log.d("Onbording", "Let-me-create-pls");

        btn = findViewById<Button>(R.id.button);
        btn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java);
            startActivity(intent);
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Onbording", "Let-me-start-pls");
    }

    override fun onResume() {
        super.onResume()
        Log.d("Onbording", "Let-me-resume-pls");
    }

    override fun onStop() {
        super.onStop();
        Log.d("Onbording", "Let-me-stop-pls");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Onbording", "Let-me-destroy-pls");
    }
}