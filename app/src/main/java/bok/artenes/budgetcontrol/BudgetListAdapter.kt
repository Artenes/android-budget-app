package bok.artenes.budgetcontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_budget.view.*

class BudgetListAdapter :
    ListAdapter<BudgetItem, BudgetListAdapter.BudgetViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget, parent, false)
        return BudgetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val budget = getItem(position)
        holder.bind(budget)
    }

    class BudgetViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(budget: BudgetItem) {
            view.textViewDescription.text = budget.description
            view.textViewValue.text = budget.value
            view.textViewDate.text = budget.date
        }

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BudgetItem>() {

            override fun areItemsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }

    }

}