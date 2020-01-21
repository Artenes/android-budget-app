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
import java.text.DateFormat
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
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        val formattedDate = formatter.format(calendar.time)
        setText(formattedDate)
    }

    override fun onClick(v: View?) {
        val fragmentManager =
            ((context as ContextThemeWrapper).baseContext as AppCompatActivity).supportFragmentManager
        picker.show(fragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    override fun onPositiveButtonClick(selection: Long) {
        val calendar = Calendar.getInstance()
        //TODO: selection is one day off
        calendar.time = Date(selection)
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