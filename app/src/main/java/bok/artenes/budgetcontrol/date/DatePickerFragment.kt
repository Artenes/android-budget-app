package bok.artenes.budgetcontrol.date

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val initialDate: Long
        get() {
            val default = Calendar.getInstance().timeInMillis
            return arguments?.getLong(INITIAL_DATE_KEY, default) ?: default
        }

    var dateListener: OnDateSelectListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = initialDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        dateListener?.onDateSelected(calendar)
    }

    companion object {
        const val INITIAL_DATE_KEY = "INITIAL_DATE_KEY"

        fun make(initialDate: Calendar = Calendar.getInstance()): DatePickerFragment {
            val arguments = Bundle()
            arguments.putLong(INITIAL_DATE_KEY, initialDate.timeInMillis)
            val fragment = DatePickerFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    interface OnDateSelectListener {
        fun onDateSelected(calendar: Calendar)
    }

}