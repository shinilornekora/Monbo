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
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileWriter

fun deleteFileAndSaveData(context: Context, fileName: String = "ORLOV_16.txt", backupKey: String = "backup_data") {
    val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(downloadDir, fileName)

    if (file.exists()) {
        val content = file.readText()
        file.delete()

        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(backupKey, content).apply()
        Toast.makeText(context, "Файл удалён и данные сохранены", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Файл для удаления не найден", Toast.LENGTH_SHORT).show()
    }
}

fun restoreFileFromBackup(context: Context, fileName: String = "ORLOV_16.txt", backupKey: String = "backup_data") {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val savedData = sharedPreferences.getString(backupKey, null)

    if (savedData != null) {
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadDir, fileName)

        try {
            FileWriter(file).use { writer ->
                writer.write(savedData)
            }
            Toast.makeText(context, "Файл восстановлен", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } else {
        Toast.makeText(context, "Файл для удаления не найден", Toast.LENGTH_SHORT).show()
    }
}

fun isBackupExists(context: Context, backupKey: String = "backup_data"): Boolean {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val savedData = sharedPreferences.getString(backupKey, null)

    return savedData !== null;
}

fun isFileExistsInDownloads(fileName: String): Boolean {
    val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val file = File(downloadDir, fileName)
    return file.exists()
}

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private val fileName = "ORLOV_16.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        loadSettings()
        updateButtonStatesAndStatuses()

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
            // TODO: later give it a chance to save somewhere else
        }

        binding.buttonDeleteImportedData.setOnClickListener {
            deleteFileAndSaveData(requireContext())
            updateButtonStatesAndStatuses()
        }

        binding.buttonRestoreBackup.setOnClickListener {
            restoreFileFromBackup(requireContext())
            updateButtonStatesAndStatuses()
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

    private fun updateButtonStatesAndStatuses() {
        val isFilePresent = isFileExistsInDownloads(fileName)
        val isBackupPresent = isBackupExists(requireContext())

        binding.buttonDeleteImportedData.isEnabled = isFilePresent
        binding.buttonRestoreBackup.isEnabled = isBackupPresent

        binding.fileStatusText.text = if (isFilePresent) {
            "Файл: найден в /Downloads"
        } else {
            "Файл: не найден в /Downloads"
        }

        binding.backupStatusText.text = if (isBackupPresent) {
            "Резервная копия: существует в приложении"
        } else {
            "Резервная копия: не найдена"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
