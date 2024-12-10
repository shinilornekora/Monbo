package com.example.onboardactivity

import PersonAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboardactivity.databinding.GameOfFireAndIceFragmentBinding
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private var _binding: GameOfFireAndIceFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PersonAdapter
    private lateinit var repository: GOTCharacterRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameOfFireAndIceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = GOTCharacterDatabase.getInstance(requireContext())
        val dao = database.characterDao()
        val api = GameApiService()
        repository = GOTCharacterRepository(dao, api)

        setupRecyclerView()

        lifecycleScope.launch {
            loadData()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                refreshData()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        observeData()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PersonAdapter(emptyList(), repository, lifecycleScope)
        binding.recyclerView.adapter = adapter
    }

    private suspend fun loadData() {
        adapter.loadData(requireContext())
    }

    private suspend fun refreshData() {
        adapter.refreshData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            repository.observeCharactersFlow().collect { newData ->
                adapter.updateData(newData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
