package com.example.tp6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp6.adaptors.CityAdaptor
import com.example.tp6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),CityAdaptor.OnCityClickListener {
    private val cities = arrayOf("Tunis", "Paris", "Londres", "Pekin",
        "Tokyo", "Ottawa", "Alger", "Moscou", "Berlin", "Madrid", "Montevideo",
        "Buenos Aires","Sousse")
    private val baseUrl="https://api.openweathermap.org/data/2.5/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.resView.adapter=CityAdaptor(cities,this)


    }

    override fun OnCityClick(city: String) {
        Toast.makeText(this,city,Toast.LENGTH_LONG).show()
    }
}