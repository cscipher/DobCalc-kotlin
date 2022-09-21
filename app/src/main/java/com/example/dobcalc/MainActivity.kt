package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelected : TextView? = null
    private var tvTime : TextView? = null


    private fun showToast(label: String) {
        Toast.makeText(this, label, Toast.LENGTH_LONG ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateBtn: Button = findViewById(R.id.buttonDatePicker)
        tvSelected = findViewById(R.id.tvSelectedDate)
        tvTime = findViewById(R.id.tvTime)
        dateBtn.setOnClickListener {
            openDatePicker()

        }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, yearS, monthS, dayOfMonthS ->
                val newDate = "$monthS/$dayOfMonthS/$yearS"
                tvSelected?.setText(newDate)
                tvTime?.setText(calculateAge(newDate))
            },
            year,
            month,
            day
        ).show()

    }

    private fun calculateAge(age: String) : String {
        var calculatedAge : String;
        val sdf = java.text.SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        val date = sdf.parse(age)
        val dateInMin = date.time / 60000
        val currentTillTime = sdf.parse(sdf.format(System.currentTimeMillis())).time
        calculatedAge = (currentTillTime - dateInMin).toString()
        return calculatedAge
    }

}