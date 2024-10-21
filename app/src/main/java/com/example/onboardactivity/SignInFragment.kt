package com.example.onboardactivity

import UserCredential
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignInFragment : Fragment(R.layout.sign_in_fragment) {
    private lateinit var loginButton: Button
    private lateinit var regButton: Button
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var welcomeText: TextView
    private var _name: String? = null
    private var _email: String? = null

    private val verySafeStorage = mutableListOf<UserCredential>(
        UserCredential("test@test.ru", "test@test.ru", "123213")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SignIn", "Let-me-create-pls")
        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)

        emailInput = view.findViewById(R.id.email)
        passwordInput = view.findViewById(R.id.pass)
        loginButton = view.findViewById(R.id.button)
        regButton = view.findViewById(R.id.reg_button)
        welcomeText = view.findViewById(R.id.welcome_text)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val userCredential = arguments?.getParcelable<UserCredential>("verySafeStorage")

            if (userCredential != null) {
                verySafeStorage.add(userCredential)
            }

            val match = verySafeStorage.any {
                it.email == email && it.password == password
            }

            if (match) {
                Toast.makeText(context, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                val activity = requireActivity() as MainActivity
                activity.navigateToHome(null);
            } else {
                Toast.makeText(context, "Неверный email или пароль.", Toast.LENGTH_SHORT).show()
            }
        }

        regButton.setOnClickListener {
            val activity = requireActivity() as MainActivity
            activity.navigateToSignUp(null);
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignIn", "Let-me-start-pls")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignIn", "Let-me-resume-pls")

        _name = arguments?.getString("welcomeName")
        _email = arguments?.getString("autoEmailCompletion")

        Log.d("SignIn", " - NAME: $_name, EMAIL: $_email")

        if (_name != null && _email != null) {
            welcomeText.text = "Привет, $_name!"
            emailInput.setText(_email)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignIn", "Let-me-stop-pls")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignIn", "Let-me-destroy-pls")
    }
}