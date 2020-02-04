package bok.artenes.budgetcontrol.money

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class MoneyTextInputEditText : TextInputEditText, TextWatcher {

    var value: Money = Money(0)
        set(value) {
            var formattedValue = formatter.format(value)
            if (formattedValue.length <= MAX_CHARACTERS) {
                field = value
                listener?.onValueChanged(value)
            } else {
                formattedValue = formatter.format(this.value)
            }
            setText(formattedValue)
            setSelection(formattedValue.length)
        }

    var listener: OnValueChangeListener? = null

    private val parser = MoneyParser()

    private val formatter = MoneyFormatter()

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val rawValue = if (text?.isNotEmpty() == true) text.toString() else "0"
        value = parser.parse(rawValue)
        addTextChangedListener(this)
    }

    override fun afterTextChanged(value: Editable) {
        removeTextChangedListener(this)
        this.value = parser.parse(value.toString())
        addTextChangedListener(this)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        //not used
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //not used
    }

    companion object {
        const val MAX_CHARACTERS = 20
    }

    interface OnValueChangeListener {
        fun onValueChanged(newValue: Money)
    }

}