package com.ciechu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ciechu.core.room.easy.EntityResultsEasy
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.matchpairsmemorygame.R
import com.ciechu.matchpairsmemorygame.R.drawable.*
import kotlinx.android.synthetic.main.game_easy.*
import org.koin.android.ext.android.inject


class GameEasy : AppCompatActivity() {

    private val easyViewModel: ScoreEasyViewModel by inject()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_easy)

        val listImages: MutableList<Int> = mutableListOf(
            asistante, asistante,
            chef, chef,
            king, king,
            police_women, police_women,
            speaker, speaker,
            superman, superman
        )
        val buttons = arrayOf(
            button, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12
        )

        val cardBack = ic_launcher_background
        var clicked = 0
        var lastClicked = -1
        var numberMoves = 0
        var pairs = 0

        listImages.shuffle()

        for (i in 0..11) {
            buttons[i].text = "back"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "back") {
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
                        clicked = 0
                        pairs++
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
                if (pairs == 6) {
                    val score =
                        EntityResultsEasy(
                            numberMoves
                        )
                    easyViewModel.insert(score)
                    startActivity(Intent(applicationContext, ResultsEasy::class.java).apply {
                        putExtra("Results", numberMoves)
                    })
                }
            }
        }
    }

    private fun buttonFalseClickable() {
        button.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        button7.isClickable = false
        button8.isClickable = false
        button9.isClickable = false
        button10.isClickable = false
        button11.isClickable = false
        button12.isClickable = false
    }

    private fun buttonTrueClickable() {
        button.isClickable = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
        button5.isClickable = true
        button6.isClickable = true
        button7.isClickable = true
        button8.isClickable = true
        button9.isClickable = true
        button10.isClickable = true
        button11.isClickable = true
        button12.isClickable = true
    }
}