package com.example.fitnesstracker.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.fitnesstracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog: DialogFragment() {

    private var yesListener: (() -> Unit)? = null

    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                .setTitle(getString(R.string.cancelTrackingDialog_cancel_run))
                .setMessage(getString(R.string.cancelTrackingDialog_are_you_sure))
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton(getString(R.string.cancelTrackingDialog_yes)) { _, _ ->
                    yesListener?.let { yes ->
                        yes()
                    }
                }
                .setNegativeButton(getString(R.string.cancelTrackingDialog_no)) { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .create()
    }
}