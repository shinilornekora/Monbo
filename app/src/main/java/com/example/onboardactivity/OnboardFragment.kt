package com.example.onboardactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboardactivity.databinding.OnboardFragmentBinding

class OnboardFragment : Fragment() {
    private var _binding: OnboardFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Onboarding", "ФРАГМЕНТ_СОЗДАН")

        _binding = OnboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_signInFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Onboarding", "ФРАГМЕНТ_СТАРТАНУЛ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Onboarding", "ФРАГМЕНТ_ПРОДОЛЖИЛСЯ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Onboarding", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Onboarding", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
