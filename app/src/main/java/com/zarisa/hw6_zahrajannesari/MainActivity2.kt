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
        }
    }

    private fun eachButtonOnClick(view: Button) {
        if ((view as Button).text.isBlank()) {
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
                    Toast.makeText(this,"player X winner",Toast.LENGTH_SHORT).show()
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
        var checkArray = arrayOf<ArrayList<String>>(arrayListOf(), arrayListOf(), arrayListOf())
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                checkArray[i][j]=buttonArray[i][j].text.toString()
            }
        }
        for (column in 0 until 3){
            if (checkArray[column][0]!=""
                &&checkArray[column][0]==checkArray[column][1]
                &&checkArray[column][0]==checkArray[column][2]){
                return true
            }
        }
        for (row in 0 until 3){
            if (checkArray[0][row]!=""
                &&checkArray[0][row]==checkArray[0][row]
                &&checkArray[0][row]==checkArray[0][row]){
                return true
            }
        }
        if (checkArray[0][0]!=""
            &&checkArray[0][0]==checkArray[1][1]
            &&checkArray[0][0]==checkArray[2][2]){
            return true
        }
        if (checkArray[0][2]!=""
            &&checkArray[0][2]==checkArray[1][1]
            &&checkArray[0][2]==checkArray[2][0]){
            return true
        }
        return false
    }
}
