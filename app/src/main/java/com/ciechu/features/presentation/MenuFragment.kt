package com.ciechu.features.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ciechu.features.presentation.dialogFragment.LevelSelectionDialogFragment
import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class MenuFragment : Fragment() {

    private val levelSelectionDialogFragment: LevelSelectionDialogFragment by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Match Pairs Memory Game"
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        new_game_bt?.setOnClickListener {
            levelSelectionDialogFragment.setTargetFragment(this, 1)
            levelSelectionDialogFragment.show(parentFragmentManager, "SelectLevelDialog")
        }

        score_menu_bt?.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToResultsEasyFragment())
        }

        github_link_tv?.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/ciechanek1?tab=repositories")
            startActivity(openURL)
        }
    }
}