package com.example.android.magichat

import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.hardware.SensorManager
import java.lang.Math.abs


class MainActivity : AppCompatActivity(), SensorEventListener {

    val SENSITIVITY = 10.0

    private val mSM: SensorManager by lazy{
        getSystemService(SENSOR_SERVICE) as SensorManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ib = findViewById(R.id.imageButton) as ImageButton
        ib.setOnClickListener{
            ib.setImageResource(R.drawable.rabbit)
        }

    }

    fun resetImage(){
        val ib = findViewById(R.id.imageButton) as ImageButton

        ib.setImageResource(R.drawable.hat)
    }

   override fun onSensorChanged(sensorEvent:SensorEvent){
        val values = sensorEvent.values
        var xAcc = values[0]
        var yAcc = values[1]

        if(abs(xAcc)>SENSITIVITY || abs(yAcc)>SENSITIVITY) {
            resetImage()
        }
    }

    override fun onResume() {
        super.onResume()
        mSM.registerListener(
                this,
                mSM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }
}
