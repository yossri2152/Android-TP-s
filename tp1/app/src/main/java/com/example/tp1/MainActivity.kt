package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "This App is developed by Yesmine", Toast.LENGTH_LONG).show()

        // Récupérer le bouton
        val buttonGoToActivity2 = findViewById<Button>(R.id.buttonGoToActivity2)

        // Ajouter un événement de clic pour changer d'activité
        buttonGoToActivity2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
