package bok.artenes.budgetcontrol

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_budget_creator.*
import java.util.*

class BudgetCreatorFragment : Fragment(), TextWatcher {

    private lateinit var moneyFormatter: MoneyFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moneyFormatter = MoneyFormatter(Locale.getDefault())
    }

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
                textInputPrice.text.toString()
            )
        }

        textInputPrice.addTextChangedListener(this)

    }

    override fun afterTextChanged(string: Editable) {
        textInputPrice.removeTextChangedListener(this)

        val formattedValue = moneyFormatter.format(string.toString())
        textInputPrice.setText(formattedValue)
        textInputPrice.setSelection(formattedValue.length)

        textInputPrice.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}