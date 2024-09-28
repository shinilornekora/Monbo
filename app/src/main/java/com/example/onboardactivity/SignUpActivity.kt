package com.example.onboardactivity

import UserCredential
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var actionButton: Button;
    private lateinit var loginButton: Button;
    private lateinit var nameInput: EditText;
    private lateinit var emailInput: EditText;
    private lateinit var passwordInput: EditText;
    private lateinit var passAgainInput: EditText;

    private var verySafeStorage = listOf<Map<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        nameInput = findViewById<EditText>(R.id.name);
        emailInput = findViewById<EditText>(R.id.email);
        passwordInput = findViewById<EditText>(R.id.pass);
        passAgainInput = findViewById<EditText>(R.id.pass_verify);

        actionButton = findViewById<Button>(R.id.registry_button);
        loginButton = findViewById<Button>(R.id.auth_button);

        actionButton.setOnClickListener {
            val name = nameInput.text.toString();
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val passVerify = passAgainInput.text.toString()

            if (password != passVerify) {
                Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            // Способ сделать это через дата-класс
//            val userCredential = UserCredential(mapOf(
//                "name" to name,
//                "email" to email,
//                "password" to password
//            ))

            // Способ сделать это через обычную строку
            val userCredential = "$email;$password"

            val intent = Intent(this, SignInActivity::class.java);
            intent.putExtra("verySafeStorage", userCredential)
            intent.putExtra("welcomeName", name);
            intent.putExtra("autoEmailCompletion", email);
            startActivity(intent);
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java);
            startActivity(intent);
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignUp", "Let-me-start-pls");
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignUp", "Let-me-resume-pls");
    }

    override fun onStop() {
        super.onStop();
        Log.d("SignUp", "Let-me-stop-pls");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUp", "Let-me-destroy-pls");
    }
}
