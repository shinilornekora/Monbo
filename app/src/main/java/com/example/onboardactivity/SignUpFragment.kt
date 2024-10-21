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
        Log.d("SignUp", "Let-me-create-pls")
        val view = inflater.inflate(R.layout.sign_up_fragment, container, false)
        val activity = requireActivity() as MainActivity

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

            activity.navigateToSignIn(bundle);
        }

        loginButton.setOnClickListener {
            activity.navigateToSignIn(null)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignUp", "Let-me-start-pls")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignUp", "Let-me-resume-pls")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignUp", "Let-me-stop-pls")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUp", "Let-me-destroy-pls")
    }
}
