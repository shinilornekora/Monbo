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
import androidx.navigation.fragment.findNavController

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
        Log.d("SignIn", "ФРАГМЕНТ_СОЗДАН")
        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)

        emailInput = view.findViewById(R.id.email)
        passwordInput = view.findViewById(R.id.pass)
        loginButton = view.findViewById(R.id.button)
        regButton = view.findViewById(R.id.reg_button)
        welcomeText = view.findViewById(R.id.welcome_text)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val match = verySafeStorage.any {
                it.email == email && it.password == password
            }

            if (match) {
                Toast.makeText(context, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Неверный email или пароль.", Toast.LENGTH_SHORT).show()
            }
        }

        regButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignIn", "ФРАГМЕНТ_СТАРТАНУЛ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignIn", "ФРАГМЕНТ_ПРОДОЛЖИЛСЯ")

        _name = arguments?.getString("welcomeName")
        _email = arguments?.getString("autoEmailCompletion")

        val newUser: UserCredential? = arguments?.getParcelable("verySafeStorage")

        if (newUser != null) {
            verySafeStorage.add(newUser)
        }

        Log.d("SignIn", " - NAME: $_name, EMAIL: $_email")

        if (_name != null && _email != null) {
            welcomeText.text = "Привет, $_name!"
            emailInput.setText(_email)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignIn", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignIn", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
