package com.example.tp5

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private var currentSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        // Initialisation du SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Désabonnement du capteur précédent
        if (currentSensor != null) {
            sensorManager.unregisterListener(this)
        }

        when (item.itemId) {
            R.id.acc -> { // Accéléromètre
                currentSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                if (currentSensor != null) {
                    sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL)
                    Toast.makeText(this, "Accéléromètre activé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Accéléromètre non disponible", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.gyr -> { // Gyroscope
                currentSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
                if (currentSensor != null) {
                    sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL)
                    Toast.makeText(this, "Gyroscope activé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gyroscope non disponible", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.mag -> { // Magnétomètre
                currentSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                if (currentSensor != null) {
                    sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL)
                    Toast.makeText(this, "Magnétomètre activé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Magnétomètre non disponible", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.pro -> { // Capteur de proximité
                currentSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                if (currentSensor != null) {
                    sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL)
                    Toast.makeText(this, "Capteur de proximité activé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Capteur de proximité non disponible", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.photo -> { // Capteur de lumière
                currentSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
                if (currentSensor != null) {
                    sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL)
                    Toast.makeText(this, "Capteur de lumière activé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Capteur de lumière non disponible", Toast.LENGTH_SHORT).show()
                }

            }
            R.id.nav_sensors -> {
                val intent = Intent(this, SensorsActivity::class.java)
                startActivity(intent)
                true
            }
        }

        // Fermer le drawer après sélection
        binding.drawerLayout.closeDrawers()
        return true
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                // Afficher les valeurs de l'accéléromètre
                Log.i("accelero","$x,$y,$z")
            }
            Sensor.TYPE_GYROSCOPE -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                // Afficher les valeurs du gyroscope
                Log.i("gyrosco","$x,$y,$z")
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                // Afficher les valeurs du magnétomètre
                Log.i("magneto","$x,$y,$z")
            }
            Sensor.TYPE_PROXIMITY -> {
                val distance = event.values[0]
                // Afficher la valeur de proximité
                Log.i( "Proximité:" ,"$distance cm" )
            }
            Sensor.TYPE_LIGHT -> {
                val light = event.values[0]
                // Afficher la valeur de luminosité
                Log.i("Luminosité:" ,"$light lx")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Gestion des changements de précision (optionnel)
    }

    override fun onPause() {
        super.onPause()
        // Désabonnement pour économiser la batterie
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        // Réabonnement si un capteur était sélectionné
        currentSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}