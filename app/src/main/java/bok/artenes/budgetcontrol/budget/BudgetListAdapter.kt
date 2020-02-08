package bok.artenes.budgetcontrol.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.item_budget.view.*

class BudgetListAdapter(private val listener: BudgetListListener) :
    ListAdapter<BudgetItem, BudgetListAdapter.BudgetViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget, parent, false)
        return BudgetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val budget = getItem(position)
        holder.bind(budget)
    }

    inner class BudgetViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        fun bind(budget: BudgetItem) {
            view.textViewDescription.text = budget.description
            view.textViewValue.text = budget.value
            view.textViewDate.text = budget.date
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val budget = getItem(adapterPosition)
            listener.onBudgetClicked(budget)
        }
    }

    interface BudgetListListener {
        fun onBudgetClicked(budget: BudgetItem)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BudgetItem>() {

            override fun areItemsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }

    }

}