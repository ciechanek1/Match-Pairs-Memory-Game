package com.ciechu.matchpairsmemorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ciechu.matchpairsmemorygame.R.drawable.*
import com.ciechu.matchpairsmemorygame.room.EntityResultsHard
import com.ciechu.matchpairsmemorygame.viewModel.ScoreHardViewModel
import kotlinx.android.synthetic.main.game_hard.*
import org.koin.android.ext.android.inject

class GameHard : AppCompatActivity() {

    private val hardViewModel: ScoreHardViewModel by inject()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_hard)

        val listImages: MutableList<Int> = mutableListOf(
            animal_bear,
            animal_bear,
            animal_camel,
            animal_camel,
            animal_cat,
            animal_cat,
            animal_cow,
            animal_cow,
            animal_cheetah,
            animal_cheetah,
            animal_kangoroo,
            animal_kangoroo,
            animal_gorilla,
            animal_gorilla,
            animal_sheep,
            animal_sheep,
            animal_wolf,
            animal_wolf,
            animal_mouse,
            animal_mouse
        )

        val buttons = arrayOf(
            button13,
            button14,
            button15,
            button16,
            button17,
            button18,
            button19,
            button20,
            button21,
            button22,
            button23,
            button24,
            button25,
            button26,
            button27,
            button28,
            button29,
            button30,
            button31,
            button32
        )

        val cardBack = ic_launcher_background
        var clicked = 0
        var lastClicked = -1
        var numberMoves = 0
        var pairs = 0

        listImages.shuffle()

        for (i in 0..19) {
            buttons[i].text = "back"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "back" /*&& !turnOver*/) {
                    buttons[i].setBackgroundResource(listImages[i])
                    buttons[i].setText(listImages[i])
                    if (clicked == 0) lastClicked = i
                    clicked++
                    numberMoves++
                }
                if (clicked == 2) {
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        pairs++
                        clicked = 0
                    } else {
                        buttonFalseClickable()
                        buttons[i].text = "back"
                        buttons[lastClicked].text = "back"
                        Handler().postDelayed({
                            buttons[i].setBackgroundResource(cardBack)
                            buttons[lastClicked].setBackgroundResource(cardBack)
                            clicked = 0
                            buttonTrueClickable()
                        }, 800)
                    }
                }
                if (pairs == 10) {
                    val score = EntityResultsHard(numberMoves)
                    hardViewModel.insertScore(score)
                    startActivity(Intent(this, ResultsHard::class.java).apply {
                        putExtra("Results", numberMoves)
                    })
                }
            }
        }
    }

    private fun buttonFalseClickable() {
        button13.isClickable = false
        button14.isClickable = false
        button15.isClickable = false
        button16.isClickable = false
        button17.isClickable = false
        button18.isClickable = false
        button19.isClickable = false
        button20.isClickable = false
        button21.isClickable = false
        button22.isClickable = false
        button23.isClickable = false
        button24.isClickable = false
        button25.isClickable = false
        button26.isClickable = false
        button27.isClickable = false
        button28.isClickable = false
        button29.isClickable = false
        button30.isClickable = false
        button31.isClickable = false
        button31.isClickable = false
        button32.isClickable = false
    }

    private fun buttonTrueClickable() {
        button13.isClickable = true
        button14.isClickable = true
        button15.isClickable = true
        button16.isClickable = true
        button17.isClickable = true
        button18.isClickable = true
        button19.isClickable = true
        button20.isClickable = true
        button21.isClickable = true
        button22.isClickable = true
        button23.isClickable = true
        button24.isClickable = true
        button25.isClickable = true
        button26.isClickable = true
        button27.isClickable = true
        button28.isClickable = true
        button29.isClickable = true
        button30.isClickable = true
        button31.isClickable = true
        button31.isClickable = true
        button32.isClickable = true
    }
}