package com.kapil.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val diceImage : ImageView = findViewById(R.id.diceImage)
        val diceRoll : Button = findViewById(R.id.diceroll)
        diceImage.contentDescription = diceRoll.toString()
        diceRoll.setOnClickListener{

           val imageResource = when(Dice(6).roll()){
                1->R.drawable.dice_1
                2->R.drawable.dice_2
                3->R.drawable.dice_3
                4->R.drawable.dice_4
                5->R.drawable.dice_5
                6->R.drawable.dice_6
               else -> R.drawable.dice_6
           }
            diceImage.setImageResource(imageResource)
            Toast.makeText(this,"Dice Rolled",Toast.LENGTH_SHORT).show()
        }
    }

}