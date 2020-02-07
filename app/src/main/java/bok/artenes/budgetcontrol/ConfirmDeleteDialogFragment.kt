package bok.artenes.budgetcontrol

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment : DialogFragment() {

    var listener: OnConfirmDeleteListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.delete_account)
        builder.setMessage(R.string.budgets_associated_with_this_account)
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            listener?.onDeleteConfirmed()
        }
        builder.setNegativeButton(android.R.string.no, null)
        return builder.create()
    }

    interface OnConfirmDeleteListener {
        fun onDeleteConfirmed()
    }

}