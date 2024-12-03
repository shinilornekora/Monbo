package com.example.onboardactivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.onboardactivity.databinding.SettingsFragmentBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        loadSettings()

        binding.switchHideAvatars.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("hide_avatars", isChecked).apply()
        }

        binding.switchAllowGroups.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("allow_groups", isChecked).apply()
        }

        binding.switchPushNotifications.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("push_notifications", isChecked).apply()
        }

        binding.buttonSelectFolder.setOnClickListener {
            // TODO: надо уметь выбирать папку
        }

        binding.buttonDeleteImportedData.setOnClickListener {
            // TODO: надо уметь удалять импортированные данные
        }

        binding.buttonRestoreBackup.setOnClickListener {
            // TODO: надо уметь восстанавливать данные из резервной копии
        }

        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun loadSettings() {
        binding.switchHideAvatars.isChecked = sharedPreferences.getBoolean("hide_avatars", false)
        binding.switchAllowGroups.isChecked = sharedPreferences.getBoolean("allow_groups", false)
        binding.switchPushNotifications.isChecked = sharedPreferences.getBoolean("push_notifications", true)

        val email = sharedPreferences.getString("user_email", "Your e-mail")
        binding.email.text = email

        lifecycleScope.launch {
            val folderPath = sharedPreferences.getString("import_folder", "/Downloads")
            binding.textFolderPath.text = "Использовать для импорта папку: $folderPath"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
