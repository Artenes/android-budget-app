package bok.artenes.budgetcontrol

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LiveData
import bok.artenes.budgetcontrol.money.Money
import bok.artenes.budgetcontrol.money.MoneyTextInputEditText
import com.google.android.material.textfield.TextInputLayout

object Bindings {

    @BindingAdapter("error")
    @JvmStatic
    fun setError(view: TextInputLayout, value: LiveData<Int>) {
        val context = view.context
        val stringId = value.value
        if (stringId != null) {
            val message = context.getString(stringId)
            view.error = message
        } else {
            view.error = null
        }
    }

    @BindingAdapter("visibleWhen")
    @JvmStatic
    fun setVisibility(view: View, isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        view.visibility = visibility
    }

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