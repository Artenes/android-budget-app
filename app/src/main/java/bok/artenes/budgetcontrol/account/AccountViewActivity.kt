package bok.artenes.budgetcontrol.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bok.artenes.budgetcontrol.ConfirmDeleteDialogFragment
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.databinding.ActivityAccountViewBinding

class AccountViewActivity : AppCompatActivity(),
    ConfirmDeleteDialogFragment.OnConfirmDeleteListener {

    private val accountUid: String?
        get() = intent?.extras?.getString(EXTRA_UID)

    private val viewModel by lazy {
        val factory = AccountViewViewModel.Factory(accountUid)
        ViewModelProviders.of(this, factory).get(AccountViewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityAccountViewBinding>(
            this,
            R.layout.activity_account_view
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.finishEdit.observe(this, Observer { finish() })
        viewModel.confirmDeleteDialog.observe(this, Observer {
            val dialog = ConfirmDeleteDialogFragment.make(
                getString(R.string.delete_account),
                getString(R.string.budgets_associated_with_this_account)
            )
            dialog.listener = this
            dialog.show(supportFragmentManager, DIALOG_DELETE_TAG)
        })

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
            val intent = Intent(context, AccountViewActivity::class.java)
            intent.putExtra(EXTRA_UID, uid)
            context.startActivity(intent)
        }

    }

}