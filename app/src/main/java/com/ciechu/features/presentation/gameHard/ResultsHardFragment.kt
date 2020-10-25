package com.ciechu.features.presentation.gameHard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_results_hard.*
import org.koin.android.ext.android.inject

class ResultsHardFragment : Fragment() {

    private val hardViewModel: ScoreHardViewModel by inject()
    private var optionMenu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Results hard level"
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_results_hard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bundleOfFromGameHardFragment()
        warningYesSetOnClickListener()
        warningNoSetOnClickListener()
        floatingActionButtonSetOnClickListener()
        listOfTheBestResults()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_results, menu)
        optionMenu = menu
        if (arguments != null && requireArguments().containsKey("results")) {
            optionMenu?.findItem(R.id.restart_game_bt)?.isVisible =
                true
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_results_button) {
            warning_hard_tv.isVisible = true
            yes_warning_hard_tv_bt.isVisible = true
            no_warning_hard_tv_bt.isVisible = true
        }
        if (item.itemId == R.id.main_menu_bt) findNavController().navigate(
            ResultsHardFragmentDirections.actionResultsHardFragmentToMenuFragment()
        )
        if (item.itemId == R.id.restart_game_bt) findNavController().navigate(
            ResultsHardFragmentDirections.actionResultsHardFragmentToGameHardFragment()
        )
        return super.onOptionsItemSelected(item)
    }

    private fun floatingActionButtonSetOnClickListener() {
        floatingActionButton_toEasy.setOnClickListener {
            findNavController().navigate(ResultsHardFragmentDirections.actionResultsHardFragmentToResultsEasyFragment())
        }
    }

    private fun warningNoSetOnClickListener() {
        no_warning_hard_tv_bt.setOnClickListener {
            warning_hard_tv.isVisible = false
            yes_warning_hard_tv_bt.isVisible = false
            no_warning_hard_tv_bt.isVisible = false
        }
    }

    private fun warningYesSetOnClickListener() {
        yes_warning_hard_tv_bt.setOnClickListener {
            hardViewModel.deleteAllRows()
            warning_hard_tv.isVisible = false
            yes_warning_hard_tv_bt.isVisible = false
            no_warning_hard_tv_bt.isVisible = false
            Toast.makeText(requireContext(), "All results removed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bundleOfFromGameHardFragment() {
        if (arguments != null && requireArguments().containsKey("results")) {
            results_hard.text =
                ("Congratulations! You completed the game with ${arguments?.getString("results")}" + " points")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun listOfTheBestResults() {
        hardViewModel.getAllScore().observe(viewLifecycleOwner, Observer {

            val sortedList = it.sortedBy { it.score }

            if (it.isNotEmpty()) score_hard1.text = sortedList[0].score.toString() + " Points"
            if (it.size > 1) score_hard2.text = sortedList[1].score.toString() + " Points"
            if (it.size > 2) score_hard3.text = sortedList[2].score.toString() + " Points"
            if (it.size > 3) score_hard4.text = sortedList[3].score.toString() + " Points"
            if (it.size > 4) score_hard5.text = sortedList[4].score.toString() + " Points"
        })
    }
}
