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

        hardViewModel.points.observe(viewLifecycleOwner, Observer {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "$it points"
        })

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
                    hardViewModel.points.value = hardViewModel.points.value?.plus(1)
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