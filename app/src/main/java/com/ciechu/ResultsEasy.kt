package com.ciechu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.results_easy.*
import org.koin.android.ext.android.inject

class ResultsEasy : AppCompatActivity() {

    private val easyViewModel: ScoreEasyViewModel by inject()
    private var optionMenu: Menu? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_easy)

        if (intent.hasExtra("Results")) {
            results_easy_tv.text =
                "Congratulations! You completed the game with ${intent.getSerializableExtra("Results")} moves"
        }
        yes_warning_easy_tv_bt.setOnClickListener {
            easyViewModel.deleteAllRows()
            warning_easy_tv.isVisible = false
            yes_warning_easy_tv_bt.isVisible = false
            no_warning_easy_tv_bt.isVisible = false
            Toast.makeText(applicationContext, "All results removed", Toast.LENGTH_SHORT).show()
        }
        no_warning_easy_tv_bt.setOnClickListener {
            warning_easy_tv.isVisible = false
            yes_warning_easy_tv_bt.isVisible = false
            no_warning_easy_tv_bt.isVisible = false
        }

        listOfTheBestResults()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_results_easy, menu)
        optionMenu = menu
        if (intent.hasExtra("Results")) optionMenu?.findItem(R.id.restart_easy_game_bt)?.isVisible =
            true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_easy_result_bt) {
            warning_easy_tv.isVisible = true
            yes_warning_easy_tv_bt.isVisible = true
            no_warning_easy_tv_bt.isVisible = true
        }
        if (item.itemId == R.id.main_menu_bt) startActivity(
            Intent(
                applicationContext,
                MainActivity::class.java
            )
        )
        if (item.itemId == R.id.restart_easy_game_bt) startActivity(
            Intent(
                applicationContext,
                GameEasy::class.java
            )
        )
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun listOfTheBestResults() {
        easyViewModel.getAllScore().observe(this, Observer {

            val sortedList = it.sortedBy { it.score }

            if (it.isNotEmpty()) score_easy1_tv.text = sortedList[0].score.toString() + " Points"
            if (it.size > 1) score_easy2_tv.text = sortedList[1].score.toString() + " Points"
            if (it.size > 2) score_easy3_tv.text = sortedList[2].score.toString() + " Points"
            if (it.size > 3) score_easy4_tv.text = sortedList[3].score.toString() + " Points"
            if (it.size > 4) score_eeasy5_tv.text = sortedList[4].score.toString() + " Points"
        })
    }
}