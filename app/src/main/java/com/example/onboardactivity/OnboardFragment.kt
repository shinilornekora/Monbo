package com.example.onboardactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Onboarding", "ФРАГМЕНТ_СОЗДАН")
        val view = inflater.inflate(R.layout.onboard_fragment, container, false)

        val btn: Button = view.findViewById(R.id.button)
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_signInFragment)
        }
        return view
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Onboarding", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
