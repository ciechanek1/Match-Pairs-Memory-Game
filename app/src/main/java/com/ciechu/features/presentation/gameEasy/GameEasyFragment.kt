package com.ciechu.features.presentation.gameEasy

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
import com.ciechu.core.room.easy.EntityResultsEasy
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.matchpairsmemorygame.R
import com.ciechu.matchpairsmemorygame.R.drawable.*
import kotlinx.android.synthetic.main.fragment_game_easy.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameEasyFragment : Fragment() {

    private val easyViewModel: ScoreEasyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_easy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observePoints()

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

        levelLoop(buttons, listImages, clicked, lastClicked, numberMoves, pairs, cardBack)
    }

    private fun observePoints() {
        easyViewModel.points.observe(viewLifecycleOwner, Observer {
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
        for (i in 0..11) {
            buttons[i].text = "back"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "back") {
                    buttons[i].setBackgroundResource(listImages[i])
                    buttons[i].setText(listImages[i])
                    if (clicked1 == 0) lastClicked1 = i
                    clicked1++
                    numberMoves1++
                    easyViewModel.points.value = easyViewModel.points.value?.plus(1)

                }
                if (clicked1 == 2) {
                    if (buttons[i].text == buttons[lastClicked1].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked1].isClickable = false
                        clicked1 = 0
                        pairs1++
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
                if (pairs1 == 6) {
                    val score =
                        EntityResultsEasy(
                            numberMoves1
                        )
                    easyViewModel.insert(score)
                    findNavController().navigate(
                        GameEasyFragmentDirections.actionGameEasyFragmentToResultsEasyFragment().actionId,
                        bundleOf("results" to easyViewModel.points.value.toString())
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

