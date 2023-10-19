package com.rplits.viper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.rplits.viper.databinding.AdapterUniversityBinding

class MainAdapter: RecyclerView.Adapter<UniversityViewHolder>() {
    private var universityList = mutableListOf<University>()

    var onLongTap: (Int) -> Unit = { }
    var onTap: (Int) -> Unit = { }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(movies: List<University>) {
        this.universityList = movies.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): UniversityViewHolder =
        UniversityViewHolder(AdapterUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.apply {
            bind(universityList[position])
        }

        holder.itemView.setOnClickListener {
            onTap(position)
        }

        holder.itemView.setOnLongClickListener {
            onLongTap(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return universityList.size
    }
}
class UniversityViewHolder(val binding: AdapterUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(university: University) {
        binding.p= university
    }
}