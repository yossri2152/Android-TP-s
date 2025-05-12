package com.example.tp1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMain2Binding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main2)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupération des éléments UI
        val editTextLogin = findViewById<EditText>(R.id.editTextLogin)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)
        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        val textViewDateTime = findViewById<TextView>(R.id.textViewDateTime)


        // Gestion du bouton "Sign In"
        buttonSignIn.setOnClickListener {
            val login = editTextLogin.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer votre login et mot de passe", Toast.LENGTH_SHORT).show()
            } else {
                if (login == "admin" && password == "1234") {
                    Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Gestion du bouton "Update Date and Time"
        binding.buttonUpdate.setOnClickListener {
            binding.textViewDateTime.text= Date().toString()
        }
        binding.textViewDateTime.text= Date().toString()
    }

}
