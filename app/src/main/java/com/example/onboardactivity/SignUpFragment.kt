package com.example.onboardactivity

import UserCredential
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
    private lateinit var actionButton: Button
    private lateinit var loginButton: Button
    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var passAgainInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SignUp", "ФРАГМЕНТ_СОЗДАН")
        val view = inflater.inflate(R.layout.sign_up_fragment, container, false)

        nameInput = view.findViewById(R.id.name)
        emailInput = view.findViewById(R.id.email)
        passwordInput = view.findViewById(R.id.pass)
        passAgainInput = view.findViewById(R.id.pass_verify)

        actionButton = view.findViewById(R.id.registry_button)
        loginButton = view.findViewById(R.id.auth_button)

        actionButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val passVerify = passAgainInput.text.toString()

            if (password != passVerify) {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userCredential = UserCredential(name, email, password)

            val bundle = Bundle().apply {
                putParcelable("verySafeStorage", userCredential)
                putString("welcomeName", name)
                putString("autoEmailCompletion", email)
            }

            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment, bundle)
        }

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignUp", "ФРАГМЕНТ_СТАРТАНУЛ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignUp", "ФРАГМЕНТ_ПРОДОЛЖИЛСЯ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignUp", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUp", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
