package com.example.onboardactivity

import GOTPerson
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardactivity.databinding.ItemPersonBinding // Импортируйте биндинг для item_person.xml

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
}
