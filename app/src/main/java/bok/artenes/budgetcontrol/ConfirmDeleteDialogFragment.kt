package bok.artenes.budgetcontrol

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment : DialogFragment() {

    var listener: OnConfirmDeleteListener? = null

    private val title: String
        get() = arguments?.getString(ARGUMENT_KEY_TITLE, "") ?: ""

    private val message: String
        get() = arguments?.getString(ARGUMENT_KEY_MESSAGE, "") ?: ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        if (message.isNotEmpty()) {
            builder.setMessage(message)
        }
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            listener?.onDeleteConfirmed()
        }
        builder.setNegativeButton(android.R.string.no, null)
        return builder.create()
    }

    interface OnConfirmDeleteListener {
        fun onDeleteConfirmed()
    }

    companion object {

        const val ARGUMENT_KEY_TITLE = "TITLE"
        const val ARGUMENT_KEY_MESSAGE = "MESSAGE"

        fun make(title: String, message: String = ""): ConfirmDeleteDialogFragment {
            val dialog = ConfirmDeleteDialogFragment()
            dialog.arguments = bundleOf(
                ARGUMENT_KEY_TITLE to title,
                ARGUMENT_KEY_MESSAGE to message
            )
            return dialog
        }

    }

}