import android.content.Context
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardactivity.databinding.ItemPersonBinding
import java.io.File
import java.io.FileWriter

class PersonAdapter(private val people: List<GOTPerson>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: GOTPerson) {
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

    fun saveDataToFile(context: Context, fileName: String = "ORLOV_16.txt") {
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadDir, fileName)

        try {
            FileWriter(file).use { writer ->
                people.forEach { person ->
                    writer.write(
                        "ID: ${person.index}, Name: ${person.name}, Gender: ${person.gender}, " +
                                "Species: ${person.species}, Status: ${person.status}\n"
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
