package bok.artenes.budgetcontrol.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.item_account.view.*

class AccountListAdapter(private val listener: AccountListListener) :
    ListAdapter<AccountItem, AccountListAdapter.AccountViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account)
    }

    inner class AccountViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        fun bind(account: AccountItem) {
            view.textViewName.text = account.name
            view.textViewBalance.text = account.balance
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val account = getItem(adapterPosition)
            listener.onAccountClicked(account)
        }
    }

    interface AccountListListener {
        fun onAccountClicked(account: AccountItem)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AccountItem>() {

            override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }

    }

}