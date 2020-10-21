package com.ciechu.features.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ciechu.core.room.easy.EntityResultsEasy
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_game_easy.*
import org.koin.android.ext.android.inject

class GameEasyFragment : Fragment() {

    private val easyViewModel: ScoreEasyViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game_easy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listImages: MutableList<Int> = mutableListOf(
            R.drawable.asistante, R.drawable.asistante,
            R.drawable.chef, R.drawable.chef,
            R.drawable.king, R.drawable.king,
            R.drawable.police_women, R.drawable.police_women,
            R.drawable.speaker, R.drawable.speaker,
            R.drawable.superman, R.drawable.superman
        )
        val buttons = arrayOf(
            button, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12
        )

        val cardBack = R.drawable.ic_launcher_background
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
                        buttonFalseClickable(buttons)
                        buttons[i].text = "back"
                        buttons[lastClicked].text = "back"
                        Handler().postDelayed({
                            buttons[i].setBackgroundResource(cardBack)
                            buttons[lastClicked].setBackgroundResource(cardBack)
                            clicked = 0
                            buttonTrueClickable(buttons)
                        }, 800)
                    }
                }
                if (pairs == 6) {
                    val score =
                        EntityResultsEasy(
                            numberMoves
                        )
                    easyViewModel.insert(score)
                    startActivity(Intent(requireContext(), ResultsEasy::class.java).apply {
                        putExtra("Results", numberMoves)
                    })
                }
            }
        }
    }

    private fun buttonFalseClickable(buttons: Array<Button>) {
        buttons.forEach { it.isClickable = false }
    }

    private fun buttonTrueClickable(buttons: Array<Button>) {
        buttons.forEach { it.isClickable = true }
    }
}

