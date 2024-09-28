package com.example.onboardactivity

import UserCredential
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var regButton: Button
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var welcomeText: TextView
    private var _name: String? = null
    private var _email: String? = null

    private val verySafeStorage = mutableListOf(
        mapOf("email" to "test@test.ru", "password" to "123213")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        Log.d("SignIn", "Let-me-create-pls")

        emailInput = findViewById<EditText>(R.id.email)
        passwordInput = findViewById<EditText>(R.id.pass)
        loginButton = findViewById<Button>(R.id.button)
        regButton = findViewById<Button>(R.id.reg_button)
        welcomeText = findViewById(R.id.welcome_text)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

//            val receivedStorageString = intent.getStringExtra("verySafeStorage")
//
//            if (receivedStorageString !== null) {
//                verySafeStorage.addAll(receivedStorageString.split(";").map {
//                    val parts = it.split(":")
//                    val email = parts[0]
//                    val password = parts[1]
//                    name = parts[2];
//                    mapOf("email" to email, "password" to password)
//                });
//            }

            val userCredential = intent.getParcelableExtra<UserCredential>("verySafeStorage")

            if (userCredential != null) {
                verySafeStorage.add(userCredential.data)
            }

            val match = verySafeStorage.any {
                it["email"] == email &&
                it["password"] == password
            }

            if (match) {
                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неверный email или пароль.", Toast.LENGTH_SHORT).show()
            }
        }

        regButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignIn", "Let-me-start-pls");
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignIn", "Let-me-resume-pls");

        _name = intent.getStringExtra("welcomeName")
        _email = intent.getStringExtra("autoEmailCompletion")

        Log.d("SignIn", " - NAME: $_name, EMAIL: $_email")

        if (_name != null && _email != null) {
            welcomeText.text = "Привет, $_name!"
            emailInput.setText(_email);
        }
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