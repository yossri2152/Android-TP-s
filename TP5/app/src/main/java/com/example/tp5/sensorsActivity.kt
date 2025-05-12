package com.example.tp5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SensorsActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var tvSensorCount: TextView
    private lateinit var lvSensors: ListView
    private lateinit var tvValueX: TextView
    private lateinit var tvValueY: TextView
    private lateinit var tvValueZ: TextView
    private lateinit var vibrator: Vibrator
    private var currentSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        // Initialisation des vues
        tvSensorCount = findViewById(R.id.tvSensorCount)
        lvSensors = findViewById(R.id.lvSensors)
        tvValueX = findViewById(R.id.tvValueX)
        tvValueY = findViewById(R.id.tvValueY)
        tvValueZ = findViewById(R.id.tvValueZ)

        // Initialisation des services
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Récupérer la liste des capteurs
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        tvSensorCount.text = "Nombre de capteurs: ${sensorList.size}"

        // Créer un adaptateur pour la ListView
        val sensorNames = sensorList.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorNames)
        lvSensors.adapter = adapter

        // Gérer le clic sur un capteur
        lvSensors.setOnItemClickListener { _, _, position, _ ->
            // Désabonner du capteur précédent
            if (currentSensor != null) {
                sensorManager.unregisterListener(this)
            }

            // Récupérer le capteur sélectionné
            currentSensor = sensorList[position]
            currentSensor?.let {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
                Toast.makeText(this, "${it.name} activé", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_PROXIMITY -> {
                val distance = event.values[0]
                tvValueX.text = "Distance: $distance"
                tvValueY.text = "Y: -"
                tvValueZ.text = "Z: -"

                // Faire vibrer si l'objet s'éloigne (valeur augmente)
                if (distance > 5f) { // 5cm comme seuil
                    vibrator.vibrate(200) // Vibrer pendant 200ms
                }
            }
            else -> {
                // Afficher les valeurs selon le nombre d'axes
                when (event.values.size) {
                    1 -> {
                        tvValueX.text = "X: ${event.values[0]}"
                        tvValueY.text = "Y: -"
                        tvValueZ.text = "Z: -"
                    }
                    2 -> {
                        tvValueX.text = "X: ${event.values[0]}"
                        tvValueY.text = "Y: ${event.values[1]}"
                        tvValueZ.text = "Z: -"
                    }
                    else -> {
                        tvValueX.text = "X: ${event.values[0]}"
                        tvValueY.text = "Y: ${event.values[1]}"
                        tvValueZ.text = "Z: ${event.values[2]}"
                    }
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Peut être ignoré pour ce TP
    }

    override fun onPause() {
        super.onPause()
        // Désabonner pour économiser la batterie
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        // Réabonner si un capteur était sélectionné
        currentSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Désabonner définitivement
        sensorManager.unregisterListener(this)
    }
}