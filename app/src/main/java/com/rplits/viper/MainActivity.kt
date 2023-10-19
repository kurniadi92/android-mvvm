package com.rplits.viper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rplits.viper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory(UniversityRepository(applicationContext))).get(
            MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        adapter.onTap = {
            viewModel.onTap(it)
        }
        adapter.onLongTap = {
            viewModel.onLongTap(it)
        }
        viewModel.universityListUpdate.observe(this, Observer {
            adapter.setList(it)
        })
        viewModel.openDetail.observe(this, Observer {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("university_name", it.name)

            startActivity(intent)
        })

        viewModel.showToast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.onCreate()
    }
}

class ViewModelFactory constructor(private val repository: UniversityRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}