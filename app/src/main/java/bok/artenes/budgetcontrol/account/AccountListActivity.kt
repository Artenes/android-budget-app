package bok.artenes.budgetcontrol.account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.activity_main.*

class AccountListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)
        floatingActionButton.setOnClickListener {
            AccountViewActivity.start(this)
        }
    }
}
