package bok.artenes.budgetcontrol.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bok.artenes.budgetcontrol.R
import kotlinx.android.synthetic.main.fragment_account_view.*
import kotlinx.android.synthetic.main.fragment_budget_creator.buttonSave

class AccountViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProviders.of(this).get(AccountViewViewModel::class.java)

        viewModel.saveFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                activity?.finish()
            }
        })

        buttonSave.setOnClickListener {
            viewModel.save(
                textInputName.text.toString(),
                textInputBalance.value
            )
        }
    }
}