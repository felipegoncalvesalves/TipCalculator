package com.fdev.tipcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fdev.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnDone.setOnClickListener {

            val totalTableTemp = binding.tieTotal.text
            val numPeopleTemp = binding.tieNumberOfPeople.text
            val percentageTemp = binding.tiePercentage.text


            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
                ) {
                Snackbar.make(binding.tieTotal, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", nPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalAlmount", totalWithTips)
                }
                clean()
                startActivity(intent)

            }
        }
        binding.btnClear.setOnClickListener {
            clean()

        }
    }

    private fun clean (){
        binding.tieTotal.setText("")
        binding.tiePercentage.setText("")
        binding.tieNumberOfPeople.setText("")

    }
}