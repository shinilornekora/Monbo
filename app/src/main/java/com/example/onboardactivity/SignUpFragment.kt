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
import com.example.onboardactivity.databinding.SignUpFormBinding
import com.example.onboardactivity.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment() {
    private var _binding: SignUpFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SignUp", "ФРАГМЕНТ_СОЗДАН")

        _binding = SignUpFragmentBinding.inflate(inflater, container, false);

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        binding.registryButton.setOnClickListener {
            val name = binding.formContainer.name.text.toString();
            val email = binding.formContainer.email.text.toString();
            val password = binding.formContainer.pass.text.toString();
            val passVerify = binding.formContainer.passVerify.text.toString();

            if (password != passVerify) {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            val userCredential = UserCredential(name, email, password);

            val bundle = Bundle().apply {
                putParcelable("userCredentials", userCredential);
                putString("welcomeName", name);
                putString("autoEmailCompletion", email);
            }

            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment, bundle)
        }

        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
        Log.d("SignUp", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
