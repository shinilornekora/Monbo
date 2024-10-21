package com.example.onboardactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Onbording", "Let-me-create-pls")
        val view = inflater.inflate(R.layout.onboard_fragment, container, false)

        val btn: Button = view.findViewById(R.id.button)
        btn.setOnClickListener {
            val activity = requireActivity() as MainActivity
            activity.navigateToSignIn(null);
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("Onbording", "Let-me-start-pls")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Onbording", "Let-me-resume-pls")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Onbording", "Let-me-stop-pls")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Onbording", "Let-me-destroy-pls")
    }
}
