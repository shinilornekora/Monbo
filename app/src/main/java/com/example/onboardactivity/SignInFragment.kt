package com.example.onboardactivity

import UserCredential
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboardactivity.databinding.SignInFragmentBinding

class SignInFragment : Fragment() {
    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!

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

        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.pass.text.toString()

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

        binding.regButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
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

        val newUser: UserCredential? = arguments?.getParcelable("userCredentials")

        if (newUser != null) {
            verySafeStorage.add(newUser)
        }

        Log.d("SignIn", " - NAME: $_name, EMAIL: $_email")

        if (_name != null && _email != null) {
            binding.welcomeText.text = "Привет, $_name!"
            binding.email.setText(_email)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignIn", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SignIn", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
