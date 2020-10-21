package com.ciechu.features.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ciechu.MenuFragmentDirections
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        new_game_bt?.setOnClickListener {
            new_game_easy_bt.isVisible = true
            new_game_hard_bt.isVisible = true
        }
        new_game_easy_bt?.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameEasyFragment())
        }
        new_game_hard_bt?.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameHardFragment())
        }
        score_menu_bt?.setOnClickListener {
            score_easy_bt.isVisible = true
            score_hard_bt.isVisible = true
        }
        score_easy_bt?.setOnClickListener {
            val intent = Intent(requireContext(), ResultsEasy::class.java)
            startActivity(intent)
        }
        score_hard_bt?.setOnClickListener {
            val intent = Intent(requireContext(), ResultsHard::class.java)
            startActivity(intent)
        }
        github_link_tv?.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/ciechanek1?tab=repositories")
            startActivity(openURL)
        }
    }
}