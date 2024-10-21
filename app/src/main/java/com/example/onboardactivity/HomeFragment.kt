package com.example.onboardactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.home_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Home", "Let-me-create-pls")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Home", "Let-me-start-pls")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Home", "Let-me-resume-pls")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Home", "Let-me-pause-pls")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Home", "Let-me-stop-pls")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Home", "Let-me-destroy-pls")
    }
}