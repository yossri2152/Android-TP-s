package com.example.tp4

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp4.databinding.ActivityWorldBinding

class WorldActivity : AppCompatActivity() {

    private var continentSelectionne: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val continents = resources.getStringArray(R.array.Continents)
        val continentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, continents)
        continentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = continentAdapter


        val emptyPays = arrayOf<String>("Sélectionnez d'abord un continent")
        val paysAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, emptyPays)
        paysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = paysAdapter
        binding.spinner2.isEnabled = false


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                continentSelectionne = parent?.getItemAtPosition(position).toString()


                val paysArray = when (continentSelectionne) {
                    "Afrique" -> resources.getStringArray(R.array.PaysAfr)
                    "Europe" -> resources.getStringArray(R.array.PaysEur)
                    "Asie" -> resources.getStringArray(R.array.PaysAsie)
                    "Océanie" -> resources.getStringArray(R.array.PaysOc)
                    "Amérique" -> resources.getStringArray(R.array.PaysAm)
                    else -> arrayOf("Aucun pays disponible")
                }


                val newPaysAdapter = ArrayAdapter(this@WorldActivity,
                    android.R.layout.simple_spinner_item, paysArray)
                newPaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner2.adapter = newPaysAdapter
                binding.spinner2.isEnabled = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.spinner2.isEnabled = false
            }
        }


        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (binding.spinner2.isEnabled) {
                    val paysSelectionne = parent?.getItemAtPosition(position).toString()
                    if (paysSelectionne != "Aucun pays disponible") {
                        val message = "Continent $continentSelectionne; Pays $paysSelectionne"
                        Toast.makeText(this@WorldActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not yet implemented")
            }
        }
    }
}