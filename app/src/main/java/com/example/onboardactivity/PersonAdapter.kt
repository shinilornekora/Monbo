import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardactivity.GOTCharacterEntity
import com.example.onboardactivity.GOTCharacterRepository
import com.example.onboardactivity.databinding.ItemPersonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonAdapter(
    private var people: List<GOTCharacterEntity>,
    private val repository: GOTCharacterRepository,
    private val coroutineScope: CoroutineScope
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: GOTCharacterEntity) {
            binding.identifier.text = person.index.toString()
            binding.personName.text = person.name
            binding.gender.text = person.gender
            binding.species.text = person.species
            binding.status.text = person.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = people.size

    // Обновление списка данных
    fun updateData(newPeople: List<GOTCharacterEntity>) {
        people = newPeople
        notifyDataSetChanged()
    }

    // Загрузка данных из репозитория
    fun loadData(context: Context) {
        coroutineScope.launch(Dispatchers.IO) {
            val data = repository.getCharacters()
            withContext(Dispatchers.Main) {
                updateData(data)
            }
        }
    }

    // Обновление данных из API и базы
    fun refreshData() {
        coroutineScope.launch(Dispatchers.IO) {
            repository.refreshCharacters()
            val data = repository.getCharacters()
            withContext(Dispatchers.Main) {
                updateData(data)
            }
        }
    }

    // Реактивное обновление через Flow
    fun observeData(flow: Flow<List<GOTCharacterEntity>>) {
        coroutineScope.launch(Dispatchers.Main) {
            flow.collect { newData ->
                updateData(newData)
            }
        }
    }
}
