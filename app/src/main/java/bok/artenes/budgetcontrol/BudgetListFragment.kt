package bok.artenes.budgetcontrol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_budget_list.view.*

class BudgetListFragment : Fragment() {

    private lateinit var adapter: BudgetListAdapter
    private lateinit var viewModel: BudgetListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_budget_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(BudgetListViewModel::class.java)
        adapter = BudgetListAdapter()
        view.recyclerView.layoutManager = LinearLayoutManager(view.context)
        view.recyclerView.adapter = adapter
        viewModel.budgets.observe(viewLifecycleOwner, Observer<List<BudgetItem>> {
            adapter.submitList(it)
        })
    }

}