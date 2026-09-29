package org.setu.placemark

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.setu.placemark.models.PlacemarkModel

class AddEditActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText
    private lateinit var editLat: EditText
    private lateinit var editLon: EditText

    private var placemarkId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        editTitle = findViewById(R.id.editTitle)
        editDesc = findViewById(R.id.editDesc)
        editLat = findViewById(R.id.editLat)
        editLon = findViewById(R.id.editLon)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        placemarkId = intent.getLongExtra("placemark_id", 0L)

        if (placemarkId != 0L) {
            val placemark = AppData.placemarks.findOne(placemarkId)

            if (placemark != null) {
                editTitle.setText(placemark.title)
                editDesc.setText(placemark.description)
                editLat.setText(placemark.x.toString())
                editLon.setText(placemark.y.toString())
            }
        }

        btnSave.setOnClickListener {
            val title = editTitle.text.toString()
            val description = editDesc.text.toString()
            val x = editLat.text.toString().toDoubleOrNull() ?: 0.0
            val y = editLon.text.toString().toDoubleOrNull() ?: 0.0

            if (placemarkId == 0L) {
                // Create a new placemark
                val placemark = PlacemarkModel(
                    title = title,
                    description = description,
                    x = x,
                    y = y
                )

                AppData.placemarks.create(placemark)

            } else {
                // Update the existing placemark
                val placemark = PlacemarkModel(
                    id = placemarkId,
                    title = title,
                    description = description,
                    x = x,
                    y = y
                )

                AppData.placemarks.update(placemark)
            }

            finish()
        }

        btnDelete.setOnClickListener {
            if (placemarkId != 0L) {
                AppData.placemarks.delete(placemarkId)
            }

            finish()
        }
    }


}