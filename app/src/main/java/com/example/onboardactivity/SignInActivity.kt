package com.example.onboardactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.content.Intent
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    private lateinit var loginButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Log.d("SignIn", "Let-me-create-pls");

        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.pass)
        loginButton = findViewById<Button>(R.id.button)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email == "test@test.ru" && password == "123213") {
                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неверный email или пароль.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignIn", "Let-me-start-pls");
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignIn", "Let-me-resume-pls");
    }

    override fun onStop() {
        super.onStop();
        Log.d("SignIn", "Let-me-stop-pls");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignIn", "Let-me-destroy-pls");
    }
}