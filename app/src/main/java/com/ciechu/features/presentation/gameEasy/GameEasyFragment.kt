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

        easyViewModel.points.observe(viewLifecycleOwner, Observer {
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "$it points"
        })

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
                    easyViewModel.points.value = easyViewModel.points.value?.plus(1)

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

