package bok.artenes.budgetcontrol

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
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

    private lateinit var _date: Calendar

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
        date = makeUTCCalendar(System.currentTimeMillis())
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
        val picker = makePicker(date.timeInMillis)
        picker.addOnPositiveButtonClickListener(this)
        val fragmentManager =
            ((context as ContextThemeWrapper).baseContext as AppCompatActivity).supportFragmentManager
        picker.show(fragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    override fun onPositiveButtonClick(selection: Long) {
        date = makeUTCCalendar(selection)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState() as Parcelable
        val state = SavedState(superState)
        state.timestamp = _date.timeInMillis
        return state
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        date = makeUTCCalendar(savedState.timestamp)
    }

    private fun makePicker(initialTime: Long): MaterialDatePicker<Long> {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setSelection(initialTime)
        builder.setTitleText(R.string.select_a_date)
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.materialCalendarFullscreenTheme, typedValue, true)
        builder.setTheme(typedValue.data)
        return builder.build()
    }

    private fun makeUTCCalendar(timestamp: Long): Calendar {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.clear()
        calendar.timeInMillis = timestamp
        return calendar
    }

    private class SavedState : BaseSavedState {

        var timestamp: Long = 0

        constructor(parcelable: Parcelable) : super(parcelable)

        constructor(parcelIn: Parcel) : super(parcelIn) {
            timestamp = parcelIn.readLong()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeLong(timestamp)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(`in`: Parcel): SavedState {
                return SavedState(`in`)
            }

            @Suppress("UNCHECKED_CAST")
            override fun newArray(size: Int): Array<SavedState> {
                return arrayOfNulls<SavedState>(size) as Array<SavedState>
            }
        }

    }

    companion object {
        const val DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG"
    }
}