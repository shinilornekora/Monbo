import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboardactivity.GameApiService
import com.example.onboardactivity.PersonAdapter
import com.example.onboardactivity.databinding.GameOfFireAndIceFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameFragment : Fragment() {
    private var _binding: GameOfFireAndIceFragmentBinding? = null
    private val binding get() = _binding!!
    private val apiService = GameApiService()
    private lateinit var adapter: PersonAdapter

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
        setupRecyclerView()

        CoroutineScope(Dispatchers.Main).launch {
            val people = apiService.getGOTPeople()
            adapter = PersonAdapter(people)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PersonAdapter(emptyList())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
