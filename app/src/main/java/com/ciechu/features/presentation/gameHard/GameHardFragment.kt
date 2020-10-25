package com.ciechu.features.presentation.gameHard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ciechu.core.room.hard.EntityResultsHard
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import com.ciechu.matchpairsmemorygame.R
import com.ciechu.matchpairsmemorygame.R.drawable.*
import kotlinx.android.synthetic.main.fragment_game_hard.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameHardFragment : Fragment() {

    private val hardViewModel: ScoreHardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_hard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observePoints()

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

        levelLoop(buttons, listImages, clicked, lastClicked, numberMoves, pairs, cardBack)
    }

    private fun observePoints() {
        hardViewModel.points.observe(viewLifecycleOwner, Observer {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "$it points"
        })
    }

    private fun levelLoop(
        buttons: Array<Button>,
        listImages: MutableList<Int>,
        clicked: Int,
        lastClicked: Int,
        numberMoves: Int,
        pairs: Int,
        cardBack: Int
    ) {
        var clicked1 = clicked
        var lastClicked1 = lastClicked
        var numberMoves1 = numberMoves
        var pairs1 = pairs
        for (i in 0..19) {
            buttons[i].text = "back"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "back" /*&& !turnOver*/) {
                    buttons[i].setBackgroundResource(listImages[i])
                    buttons[i].setText(listImages[i])
                    if (clicked1 == 0) lastClicked1 = i
                    clicked1++
                    numberMoves1++
                    hardViewModel.points.value = hardViewModel.points.value?.plus(1)
                }
                if (clicked1 == 2) {
                    if (buttons[i].text == buttons[lastClicked1].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked1].isClickable = false
                        pairs1++
                        clicked1 = 0
                    } else {
                        buttonFalseClickable(buttons)
                        buttons[i].text = "back"
                        buttons[lastClicked1].text = "back"
                        Handler().postDelayed({
                            buttons[i].setBackgroundResource(cardBack)
                            buttons[lastClicked1].setBackgroundResource(cardBack)
                            clicked1 = 0
                            buttonTrueClickable(buttons)
                        }, 800)
                    }
                }
                if (pairs1 == 10) {
                    val score =
                        EntityResultsHard(
                            numberMoves1
                        )
                    hardViewModel.insertScore(score)
                    findNavController().navigate(
                        GameHardFragmentDirections.actionGameHardFragmentToResultsHardFragment().actionId,
                        bundleOf("results" to hardViewModel.points.value.toString())
                    )
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