package com.ciechu.matchpairsmemorygame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new_game_bt.setOnClickListener {
            new_game_easy_bt.isVisible = true
            new_game_hard_bt.isVisible = true
        }
        new_game_easy_bt.setOnClickListener {
            val intent = Intent(applicationContext, GameEasy::class.java)
            startActivity(intent)
        }
        new_game_hard_bt.setOnClickListener {
            val intent = Intent(applicationContext, GameHard::class.java)
            startActivity(intent)
        }
        score_menu_bt.setOnClickListener {
            score_easy_bt.isVisible = true
            score_hard_bt.isVisible = true
        }
        score_easy_bt.setOnClickListener {
            val intent = Intent(applicationContext, ResultsEasy::class.java)
            startActivity(intent)
        }
        score_hard_bt.setOnClickListener {
            val intent = Intent(applicationContext, ResultsHard::class.java)
            startActivity(intent)
        }
        github_link_tv.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/ciechanek1?tab=repositories")
            startActivity(openURL)
        }
    }
}