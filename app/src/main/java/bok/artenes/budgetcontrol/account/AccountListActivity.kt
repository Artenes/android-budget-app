package bok.artenes.budgetcontrol.account

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.activity_account_list.*
import kotlinx.android.synthetic.main.activity_main.floatingActionButton

class AccountListActivity : AppCompatActivity(), AccountListAdapter.AccountListListener {

    private lateinit var adapter: AccountListAdapter
    private lateinit var viewModel: AccountListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)

        viewModel = ViewModelProviders.of(this).get(AccountListViewModel::class.java)
        adapter = AccountListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.accounts.observe(this, Observer<List<AccountItem>> {
            textViewEmptyMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            recyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        })

        floatingActionButton.setOnClickListener {
            AccountViewActivity.start(this)
        }
    }

    override fun onAccountClicked(account: AccountItem) {
        AccountViewActivity.start(this, account.id)
    }

}
