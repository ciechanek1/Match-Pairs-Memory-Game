package com.ciechu.features.presentation.dialogFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ciechu.features.presentation.MenuFragmentDirections

import com.ciechu.matchpairsmemorygame.R
import kotlinx.android.synthetic.main.level_selection_dialog.view.*

class LevelSelectionDialogFragment : DialogFragment() {

    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.level_selection_dialog, null)

        builder.setView(view)

        view.buttonEasy_dialog.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameEasyFragment())
            dismiss()
        }

        view.buttonHard_dialog.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameHardFragment())
            dismiss()
        }

        return builder.create()
    }
}