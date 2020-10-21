package com.ciechu.features.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.ciechu.MainActivity
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.results_hard.*
import org.koin.android.ext.android.inject


class ResultsHard : AppCompatActivity() {

    private val hardViewModel: ScoreHardViewModel by inject()
    private var optionMenu: Menu? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_hard)

        if (intent.hasExtra("Results")) {
            results_hard.text =
                "Congratulations! You completed the game with ${intent.getSerializableExtra("Results")} moves"
        }
        yes_warning_hard_tv_bt.setOnClickListener {
            hardViewModel.deleteAllRows()
            warning_hard_tv.isVisible = false
            yes_warning_hard_tv_bt.isVisible = false
            no_warning_hard_tv_bt.isVisible = false
            Toast.makeText(applicationContext, "All results removed", Toast.LENGTH_SHORT).show()
        }
        no_warning_hard_tv_bt.setOnClickListener {
            warning_hard_tv.isVisible = false
            yes_warning_hard_tv_bt.isVisible = false
            no_warning_hard_tv_bt.isVisible = false
        }

        listOfTheBestResults()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_results_hard, menu)
        optionMenu = menu
        if (intent.hasExtra("Results")) optionMenu?.findItem(R.id.restart_game_bt)?.isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_results_button) {
            warning_hard_tv.isVisible = true
            yes_warning_hard_tv_bt.isVisible = true
            no_warning_hard_tv_bt.isVisible = true
        }
        if (item.itemId == R.id.main_menu_hard_bt) startActivity(
            Intent(
                applicationContext,
                MainActivity::class.java
            )
        )
        /* if (item.itemId == R.id.restart_game_bt) startActivity(
             Intent(
                 applicationContext,
                 GameHard::class.java
             )
         )*/

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun listOfTheBestResults() {
        hardViewModel.getAllScore().observe(this, Observer {

            val sortedList = it.sortedBy { it.score }

            if (it.isNotEmpty()) score_hard1.text = sortedList[0].score.toString() + " Points"
            if (it.size > 1) score_hard2.text = sortedList[1].score.toString() + " Points"
            if (it.size > 2) score_hard3.text = sortedList[2].score.toString() + " Points"
            if (it.size > 3) score_hard4.text = sortedList[3].score.toString() + " Points"
            if (it.size > 4) score_hard5.text = sortedList[4].score.toString() + " Points"
        })
    }
}
