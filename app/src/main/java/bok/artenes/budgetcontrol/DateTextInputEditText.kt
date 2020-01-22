package bok.artenes.budgetcontrol

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class DateTextInputEditText : TextInputEditText, View.OnClickListener,
    MaterialPickerOnPositiveButtonClickListener<Long> {

    //TODO: state is not preserved
    private lateinit var _date: Calendar

    private lateinit var picker: MaterialDatePicker<Long>

    var date: Calendar
        get() = _date
        set(value) {
            _date = value
            setFormattedDateOnField(value)
        }

    constructor(context: Context) : super(context)

    @Suppress("UsePropertyAccessSyntax")
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setFocusable(false)
        setOnClickListener(this)
        date = Calendar.getInstance()
        picker = makePicker()
        picker.addOnPositiveButtonClickListener(this)
    }

    private fun setFormattedDateOnField(calendar: Calendar) {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        val formattedDay = day.toString().padStart(2, '0')
        val formattedMonth = month.toString().padStart(2, '0')
        val formattedYear = year.toString()
        val formattedDate = "$formattedDay/$formattedMonth/$formattedYear"
        setText(formattedDate)
    }

    override fun onClick(v: View?) {
        val fragmentManager =
            ((context as ContextThemeWrapper).baseContext as AppCompatActivity).supportFragmentManager
        picker.show(fragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    override fun onPositiveButtonClick(selection: Long) {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = selection
        date = calendar
    }

    private fun makePicker(): MaterialDatePicker<Long> {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds())
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.materialCalendarFullscreenTheme, typedValue, true)
        builder.setTheme(typedValue.data)
        return builder.build()
    }

    companion object {
        const val DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG"
    }
}