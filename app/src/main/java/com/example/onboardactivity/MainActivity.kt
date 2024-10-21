package com.example.onboardactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        showFragment(OnboardFragment())
    }

    fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }

    fun navigateToSignIn(extendedData: Bundle?) {
        val signInFragment = SignInFragment();
        signInFragment.apply {
            arguments = extendedData
        };

        showFragment(signInFragment)
    }

    fun navigateToSignUp(extendedData: Bundle?) {
        val signUpFragment = SignUpFragment();
        signUpFragment.apply {
            arguments = extendedData
        };

        showFragment(signUpFragment)
    }

    fun navigateToHome(extendedData: Bundle?) {
        val homeFragment = HomeFragment();
        homeFragment.apply {
            arguments = extendedData
        };

        showFragment(homeFragment);
    }
}