package bok.artenes.budgetcontrol.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.activity_account_view.*

class AccountViewActivity : AppCompatActivity() {

    private val accountUid: String?
        get() = intent?.extras?.getString(EXTRA_UID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_view)

        val factory = AccountViewViewModel.Factory(accountUid)
        val viewModel = ViewModelProviders.of(this, factory).get(AccountViewViewModel::class.java)

        viewModel.account.observe(this, Observer {
            textInputName.setText(it.name)
            textInputBalance.setText(it.balance)
        })

        viewModel.saveFinished.observe(this, Observer {
            if (it) {
                finish()
            }
        })

        buttonSave.setOnClickListener {
            viewModel.save(
                textInputName.text.toString(),
                textInputBalance.value
            )
        }
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