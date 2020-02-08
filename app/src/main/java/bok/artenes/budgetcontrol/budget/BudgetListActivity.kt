package bok.artenes.budgetcontrol.budget

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.account.AccountListActivity
import kotlinx.android.synthetic.main.activity_budget_list.*

class BudgetListActivity : AppCompatActivity(), BudgetListAdapter.BudgetListListener {

    private lateinit var adapter: BudgetListAdapter
    private lateinit var viewModel: BudgetListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_list)

        viewModel = ViewModelProviders.of(this).get(BudgetListViewModel::class.java)
        adapter = BudgetListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.budgets.observe(this, Observer<List<BudgetItem>> {
            adapter.submitList(it)
        })

        floatingActionButton.setOnClickListener {
            BudgetViewActivity.start(this)
        }
    }

    override fun onBudgetClicked(budget: BudgetItem) {
        BudgetViewActivity.start(this, budget.uid)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAccounts -> {
                startActivity(Intent(this, AccountListActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
