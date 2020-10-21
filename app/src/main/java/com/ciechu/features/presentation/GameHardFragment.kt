package com.ciechu.features.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ciechu.core.room.hard.EntityResultsHard
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_game_hard.*
import org.koin.android.ext.android.inject

class GameHardFragment : Fragment() {

    private val hardViewModel: ScoreHardViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_hard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listImages: MutableList<Int> = mutableListOf(
            R.drawable.animal_bear,
            R.drawable.animal_bear,
            R.drawable.animal_camel,
            R.drawable.animal_camel,
            R.drawable.animal_cat,
            R.drawable.animal_cat,
            R.drawable.animal_cow,
            R.drawable.animal_cow,
            R.drawable.animal_cheetah,
            R.drawable.animal_cheetah,
            R.drawable.animal_kangoroo,
            R.drawable.animal_kangoroo,
            R.drawable.animal_gorilla,
            R.drawable.animal_gorilla,
            R.drawable.animal_sheep,
            R.drawable.animal_sheep,
            R.drawable.animal_wolf,
            R.drawable.animal_wolf,
            R.drawable.animal_mouse,
            R.drawable.animal_mouse
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

        val cardBack = R.drawable.ic_launcher_background
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
                if (pairs == 10) {
                    val score =
                        EntityResultsHard(
                            numberMoves
                        )
                    hardViewModel.insertScore(score)
                    startActivity(Intent(requireContext(), ResultsHard::class.java).apply {
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