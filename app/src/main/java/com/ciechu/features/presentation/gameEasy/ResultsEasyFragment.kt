package com.ciechu.features.presentation.gameEasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_results_easy.*
import org.koin.android.viewmodel.ext.android.viewModel

class ResultsEasyFragment : Fragment() {

    private val easyViewModel: ScoreEasyViewModel by viewModel()
    private var optionMenu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Results easy level"
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_results_easy, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bundleOfFromGameEasyFragment()
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
            warning_easy_tv.isVisible = true
            yes_warning_easy_tv_bt.isVisible = true
            no_warning_easy_tv_bt.isVisible = true
        }
        if (item.itemId == R.id.main_menu_bt) findNavController().navigate(
            ResultsEasyFragmentDirections.actionResultsEasyFragmentToMenuFragment2()
        )
        if (item.itemId == R.id.restart_game_bt) findNavController().navigate(
            ResultsEasyFragmentDirections.actionResultsEasyFragmentToGameEasyFragment()
        )
        return super.onOptionsItemSelected(item)
    }

    private fun floatingActionButtonSetOnClickListener() {
        floatingActionButton_toHard.setOnClickListener {
            findNavController().navigate(ResultsEasyFragmentDirections.actionResultsEasyFragmentToResultsHardFragment())
        }
    }

    private fun warningNoSetOnClickListener() {
        no_warning_easy_tv_bt.setOnClickListener {
            warning_easy_tv.isVisible = false
            yes_warning_easy_tv_bt.isVisible = false
            no_warning_easy_tv_bt.isVisible = false
        }
    }

    private fun warningYesSetOnClickListener() {
        yes_warning_easy_tv_bt.setOnClickListener {
            easyViewModel.deleteAllRows()
            warning_easy_tv.isVisible = false
            yes_warning_easy_tv_bt.isVisible = false
            no_warning_easy_tv_bt.isVisible = false
            Toast.makeText(requireContext(), "All results removed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bundleOfFromGameEasyFragment() {
        if (arguments != null && requireArguments().containsKey("results")) {
            results_easy_tv.text =
                ("Congratulations! You completed the game with ${arguments?.getString("results")}" + " points")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun listOfTheBestResults() {
        easyViewModel.getAllScore().observe(viewLifecycleOwner, Observer {

            val sortedList = it.sortedBy { it.score }

            if (it.isNotEmpty()) score_easy1_tv.text = sortedList[0].score.toString() + " Points"
            if (it.size > 1) score_easy2_tv.text = sortedList[1].score.toString() + " Points"
            if (it.size > 2) score_easy3_tv.text = sortedList[2].score.toString() + " Points"
            if (it.size > 3) score_easy4_tv.text = sortedList[3].score.toString() + " Points"
            if (it.size > 4) score_eeasy5_tv.text = sortedList[4].score.toString() + " Points"
        })
    }
}