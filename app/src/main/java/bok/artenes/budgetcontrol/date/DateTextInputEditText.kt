package bok.artenes.budgetcontrol.date

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import java.text.DateFormat
import java.util.*

class DateTextInputEditText : TextInputEditText, View.OnClickListener,
    DatePickerFragment.OnDateSelectListener {

    private lateinit var _date: Calendar

    var date: Calendar
        get() = _date
        set(value) {
            _date = value
            setFormattedDateOnField(value)
            listener?.onDateChanged(value)
        }

    private val fragmentManager: FragmentManager
        get() = ((context as ContextThemeWrapper).baseContext as AppCompatActivity).supportFragmentManager

    var listener: OnDateChangeListener? = null

    constructor(context: Context) : super(context)

    @Suppress("UsePropertyAccessSyntax")
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setFocusable(false)
        setOnClickListener(this)
        date = Calendar.getInstance()
    }

    private fun setFormattedDateOnField(calendar: Calendar) {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        val formattedDate = formatter.format(calendar.time)
        setText(formattedDate)
    }

    override fun onClick(v: View?) {
        val picker = DatePickerFragment.make(date)
        picker.dateListener = this
        picker.show(fragmentManager, DATE_PICKER_FRAGMENT_TAG)
    }

    override fun onDateSelected(calendar: Calendar) {
        date = calendar
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
        //restore date
        val tempDate = Calendar.getInstance()
        tempDate.timeInMillis = savedState.timestamp
        date = tempDate
        //restore dialog listener
        val fragment =
            fragmentManager.findFragmentByTag(DATE_PICKER_FRAGMENT_TAG) as DatePickerFragment?
        fragment?.dateListener = this
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

    interface OnDateChangeListener {
        fun onDateChanged(newValue: Calendar)
    }

    companion object {
        const val DATE_PICKER_FRAGMENT_TAG = "DATE_PICKER_FRAGMENT_TAG"
    }
}