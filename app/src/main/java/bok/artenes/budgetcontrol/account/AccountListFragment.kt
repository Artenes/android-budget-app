package bok.artenes.budgetcontrol.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.fragment_account_list.view.*
import kotlinx.android.synthetic.main.fragment_budget_list.view.recyclerView

class AccountListFragment : Fragment(), AccountListAdapter.AccountListListener {

    private lateinit var adapter: AccountListAdapter
    private lateinit var viewModel: AccountListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(AccountListViewModel::class.java)
        adapter = AccountListAdapter()
        adapter.accountListListener = this
        view.recyclerView.layoutManager = LinearLayoutManager(view.context)
        view.recyclerView.adapter = adapter
        viewModel.accounts.observe(viewLifecycleOwner, Observer<List<AccountItem>> {
            view.textViewEmptyMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            view.recyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        })
    }

    override fun onAccountClicked(account: AccountItem) {
        AccountViewActivity.start(context!!, account.id)
    }
}