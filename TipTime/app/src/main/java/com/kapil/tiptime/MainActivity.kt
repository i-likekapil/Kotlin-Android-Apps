package com.kapil.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kapil.tiptime.R.string.tip_amount
import com.kapil.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipResult.text = ""

        /**
         * // Old way with findViewById()
        val myButton: Button = findViewById(R.id.my_button)
        myButton.text = "A button"

        // Better way with view binding
        val myButton: Button = binding.myButton
        myButton.text = "A button"

        // Best way with view binding and no extra variable
        binding.myButton.text = "A button"
         */
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val stringInTextField=binding.costOfServiceEditText.text.toString()
        if(stringInTextField.isEmpty()){
            Toast.makeText(this,"Enter Amount please",Toast.LENGTH_SHORT).show()
        }else
        {
            val cost = stringInTextField.toDouble()
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_fifteen_percent -> 0.15
                else -> 0.10
            }
            var tip = tipPercentage * cost
            if(binding.roundUpSwitch.isChecked) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(tip_amount, formattedTip)
        }
    }
        private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                // Hide the keyboard
                val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                return true
            }
            return false
        }
}