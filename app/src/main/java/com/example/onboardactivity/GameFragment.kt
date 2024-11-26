package com.example.onboardactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onboardactivity.databinding.GameOfFireAndIceFragmentBinding

class GameFragment : Fragment() {
    private var _binding: GameOfFireAndIceFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Game", "ФРАГМЕНТ_СОЗДАН")

        _binding = GameOfFireAndIceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Game", "ФРАГМЕНТ_СОЗДАН")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Game", "ФРАГМЕНТ_СТАРТАНУЛ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Game", "ФРАГМЕНТ_ПРОДОЛЖИЛСЯ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Game", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Game", "ФРАГМЕНТ_ОСТАНОВИЛСЯ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Game", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}