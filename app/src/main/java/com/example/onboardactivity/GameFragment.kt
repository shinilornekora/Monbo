package com.example.onboardactivity

import GOTPerson
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.onboardactivity.databinding.GameOfFireAndIceFragmentBinding
import kotlinx.coroutines.launch

class GameFragment : Fragment() {
    private var _binding: GameOfFireAndIceFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var peopleData: List<GOTPerson>
    private val gameApiService = GameApiService() // Экземпляр вашего API сервиса

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
        Log.d("Game", "ФРАГМЕНТ_НАЧАЛ_ОТРИСОВКУ")

        // Если данные еще не загружены, выполняем загрузку
        if (!this::peopleData.isInitialized) {
            fetchData()
        } else {
            displayPeopleData()
        }
    }

    private fun fetchData() {
        // Используем lifecycleScope для запуска корутин
        lifecycleScope.launch {
            Log.d("Game", "НАЧАЛ ЗАГРУЗКУ ДАННЫХ")
            try {
                peopleData = gameApiService.getGOTPeople()
                Log.d("Game", "ДАННЫЕ УСПЕШНО ЗАГРУЖЕНЫ: ${peopleData.size} персонажей")
                displayPeopleData()
            } catch (e: Exception) {
                Log.e("Game", "ОШИБКА ЗАГРУЗКИ ДАННЫХ: ${e.message}")
                showError()
            }
        }
    }

    private fun displayPeopleData() {
        // Отобразить данные в интерфейсе
    }

    private fun showError() {
        // Показываем сообщение об ошибке
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Game", "ФРАГМЕНТ_ЛИКВИДИРОВАН")
    }
}
