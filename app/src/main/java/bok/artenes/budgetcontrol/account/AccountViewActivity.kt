package bok.artenes.budgetcontrol.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.databinding.ActivityAccountViewBinding

class AccountViewActivity : AppCompatActivity() {

    private val accountUid: String?
        get() = intent?.extras?.getString(EXTRA_UID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = AccountViewViewModel.Factory(accountUid)
        val viewModel = ViewModelProviders.of(this, factory).get(AccountViewViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityAccountViewBinding>(
            this,
            R.layout.activity_account_view
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.saveFinished.observe(this, Observer { finish() })
    }

    companion object {

        private const val EXTRA_UID = "UID"

        fun start(context: Context, id: String? = null) {
            val intent = Intent(context, AccountViewActivity::class.java)
            intent.putExtra(EXTRA_UID, id)
            context.startActivity(intent)
        }

    }

}