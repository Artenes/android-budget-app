package bok.artenes.budgetcontrol

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_budget_creator.*
import java.text.DateFormat
import java.util.*

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

        val builder =MaterialDatePicker.Builder.datePicker()
        builder.setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds())
        val typedValue = TypedValue()
        requireContext().theme.resolveAttribute(R.attr.materialCalendarFullscreenTheme, typedValue, true)
        builder.setTheme(typedValue.data)
        val picker = builder.build()

        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        val formatedDate = formatter.format(Date())
        textInputDate.setText(formatedDate)
        textInputDate.setOnClickListener {
            picker.show(requireFragmentManager(), "DATEPICKER")
        }

        buttonSave.setOnClickListener {
            viewModel.save(
                textInputDescription.text.toString(),
                textInputPrice.text.toString()
            )
        }
    }
}