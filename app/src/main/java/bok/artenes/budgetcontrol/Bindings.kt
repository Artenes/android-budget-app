package bok.artenes.budgetcontrol

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import bok.artenes.budgetcontrol.money.Money
import bok.artenes.budgetcontrol.money.MoneyTextInputEditText

object Bindings {

    @BindingAdapter("value")
    @JvmStatic
    fun setValue(view: MoneyTextInputEditText, newValue: Money?) {
        if (view.value != newValue) {
            view.value = newValue ?: Money(0)
        }
    }

    @InverseBindingAdapter(attribute = "value")
    @JvmStatic
    fun getValue(view: MoneyTextInputEditText): Money {
        return view.value
    }

    @BindingAdapter("app:valueAttrChanged")
    @JvmStatic
    fun setListener(view: MoneyTextInputEditText, attrChange: InverseBindingListener) {
        view.listener = object : MoneyTextInputEditText.OnValueChangeListener {
            override fun onValueChanged(newValue: Money) {
                attrChange.onChange()
            }
        }
    }

}