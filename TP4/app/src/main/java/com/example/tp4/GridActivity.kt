package com.example.tp4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp4.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityGridBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val villes= arrayOf("Monastir","Sousse","Tunis","Bizerte","Mahdia","Gabes")
        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,villes)
        binding.gridView.adapter=adapter

        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            val selectedCity = villes[position]
            binding.textView2.text = "Ville sélectionnée: $selectedCity"
            val queryUrl = "https://www.google.com/search?q=$selectedCity"
            val intent= Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
            startActivity(intent)
        }
    }
}