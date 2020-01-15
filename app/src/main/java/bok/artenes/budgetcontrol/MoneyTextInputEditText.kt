package bok.artenes.budgetcontrol

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class MoneyTextInputEditText : TextInputEditText, TextWatcher {

    lateinit var value: Money
        private set

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val value = if (text?.isNotEmpty() == true) text.toString() else "0"
        setValue(value)
        addTextChangedListener(this)
    }

    override fun afterTextChanged(value: Editable) {
        removeTextChangedListener(this)
        setValue(value.toString())
        addTextChangedListener(this)
    }

    private fun setValue(value: String) {
        val money = Money(value)
        var formattedValue = money.format()
        if (formattedValue.length <= MAX_CHARACTERS) {
            this.value = money
        } else {
            formattedValue = this.value.format()
        }
        setText(formattedValue)
        setSelection(formattedValue.length)
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
}