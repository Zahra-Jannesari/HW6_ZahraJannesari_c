package com.zarisa.hw6_zahrajannesari

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zarisa.hw6_zahrajannesari.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(){
    lateinit var binding2: ActivityMain2Binding
    private var buttonArray = arrayOf<ArrayList<Button>>(arrayListOf(), arrayListOf(), arrayListOf())
    private var player1Turn = true
    private var roundCount=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMain2Binding.inflate(layoutInflater)
        val view2 = binding2.root
        setContentView(view2)
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val buttonId = "button_$i$j"
                val resId = resources.getIdentifier(buttonId, "id", packageName)
                buttonArray[i].add(findViewById(resId))
                buttonArray[i][j].setOnClickListener{eachButtonOnClick(buttonArray[i][j])}
            }
        }
        binding2.buttonReset.setOnClickListener {
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    buttonArray[i][j].text = ""
                }
            }
            player1Turn = true
            binding2.textViewResult.text="result"
            roundCount=0
        }
    }

    private fun eachButtonOnClick(view: Button) {
        if (view.text.isBlank()&&binding2.textViewResult.text=="result") {
            if (player1Turn) {
                view.text = "X"
            } else {
                view.text = "O"
            }
            roundCount++
            if(checkWinner()){
                if (player1Turn) {
                    binding2.textViewResult.text = "player X winner"
                    Toast.makeText(this,"player X winner",Toast.LENGTH_SHORT).show()
                }
                else{
                    binding2.textViewResult.text = "player O winner"
                    Toast.makeText(this,"player O winner",Toast.LENGTH_SHORT).show()
                }
            } else if(roundCount==9) {
                binding2.textViewResult.text = "Draw"
                Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show()
            }else{
                player1Turn=!player1Turn
            }
        }
    }
    private fun checkWinner():Boolean{
        for (column in 0 until 3){
            if (buttonArray[column][0].text!=""
                &&buttonArray[column][0].text==buttonArray[column][1].text
                &&buttonArray[column][0].text==buttonArray[column][2].text){
                return true
            }
        }
        for (row in 0 until 3){
            if (buttonArray[0][row].text!=""
                &&buttonArray[0][row].text==buttonArray[0][row].text
                &&buttonArray[0][row].text==buttonArray[0][row].text){
                return true
            }
        }
        if (buttonArray[0][0].text!=""
            &&buttonArray[0][0].text==buttonArray[1][1].text
            &&buttonArray[0][0]==buttonArray[2][2]){
            return true
        }
        if (buttonArray[0][2].text!=""
            &&buttonArray[0][2].text==buttonArray[1][1].text
            &&buttonArray[0][2].text==buttonArray[2][0].text){
            return true
        }
        return false
    }
}
