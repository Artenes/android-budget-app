package bok.artenes.budgetcontrol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_budget_creator.*

class BudgetCreatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_budget_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProviders.of(this).get(BudgetCreatorViewModel::class.java)

        viewModel.saveFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                activity?.finish()
            }
        })

        buttonSave.setOnClickListener {
            viewModel.save(
                textInputDescription.text.toString(),
                textInputPrice.value,
                textInputDate.date
            )
        }
    }
}