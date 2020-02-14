package bok.artenes.budgetcontrol.budget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bok.artenes.budgetcontrol.ConfirmDeleteDialogFragment
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.SpinnerItem
import bok.artenes.budgetcontrol.databinding.ActivityBudgetViewBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_budget_view.*

class BudgetViewActivity : AppCompatActivity(),
    ConfirmDeleteDialogFragment.OnConfirmDeleteListener {

    private val accountUid: String?
        get() = intent?.extras?.getString(EXTRA_UID)

    private val viewModel by lazy {
        val factory = BudgetViewViewModel.Factory(accountUid)
        ViewModelProviders.of(this, factory).get(BudgetViewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityBudgetViewBinding>(
            this,
            R.layout.activity_budget_view
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.finishEdit.observe(this, Observer { finish() })
        viewModel.confirmDeleteDialog.observe(this, Observer {
            val dialog = ConfirmDeleteDialogFragment.make(getString(R.string.delete_budget))
            dialog.listener = this
            dialog.show(supportFragmentManager, DIALOG_DELETE_TAG)
        })

        textInputAccount.items = listOf(
            SpinnerItem("adasad", "Account A"),
            SpinnerItem("adasadasda", "Account B"),
            SpinnerItem("adasadasdaasdads", "Account C")
        )

        restoreDeleteConfirmedListener()
    }

    override fun onDeleteConfirmed() {
        viewModel.delete()
    }

    private fun restoreDeleteConfirmedListener() {
        val dialog = supportFragmentManager
            .findFragmentByTag(DIALOG_DELETE_TAG) as ConfirmDeleteDialogFragment?
        dialog?.listener = this
    }

    companion object {

        private const val EXTRA_UID = "UID"
        private const val DIALOG_DELETE_TAG = "DIALOG_DELETE_TAG"

        fun start(context: Context, uid: String? = null) {
            val intent = Intent(context, BudgetViewActivity::class.java)
            intent.putExtra(EXTRA_UID, uid)
            context.startActivity(intent)
        }

    }

}