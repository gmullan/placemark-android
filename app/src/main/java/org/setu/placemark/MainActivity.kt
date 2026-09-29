package org.setu.placemark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.setu.placemark.models.PlacemarkModel

class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        container.removeAllViews()

        for (placemark in AppData.placemarks.findAll()) {

            val textView = TextView(this)

            textView.text =
                "${placemark.title}\n" +
                        "${placemark.description}\n" +
                        "Location: ${placemark.x}, ${placemark.y}"

            textView.textSize = 18f
            textView.setPadding(8, 16, 8, 16)

            textView.setOnClickListener {
                val intent = Intent(this, AddEditActivity::class.java)
                intent.putExtra("placemark_id", placemark.id)
                startActivity(intent)
            }

            container.addView(textView)
        }
    }
}

