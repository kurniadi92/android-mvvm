package com.rplits.viper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rplits.viper.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = intent.getStringExtra("university_name")
        initView()
    }

    private fun initView() {
        binding.toolbar.root.setTitle("Detail University")
        binding.toolbar.root.setTitleTextColor(Color.WHITE)
        binding.toolbar.root.setNavigationOnClickListener { this.finish() }
    }
}