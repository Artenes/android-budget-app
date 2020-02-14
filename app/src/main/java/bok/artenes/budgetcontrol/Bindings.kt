package bok.artenes.budgetcontrol

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LiveData
import bok.artenes.budgetcontrol.date.DateTextInputEditText
import bok.artenes.budgetcontrol.money.Money
import bok.artenes.budgetcontrol.money.MoneyTextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

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
    fun setValueListener(view: MoneyTextInputEditText, attrChange: InverseBindingListener) {
        view.listener = object : MoneyTextInputEditText.OnValueChangeListener {
            override fun onValueChanged(newValue: Money) {
                attrChange.onChange()
            }
        }
    }

    @BindingAdapter("date")
    @JvmStatic
    fun setDate(view: DateTextInputEditText, newValue: Calendar?) {
        if (view.date != newValue) {
            view.date = newValue ?: Calendar.getInstance()
        }
    }

    @InverseBindingAdapter(attribute = "date")
    @JvmStatic
    fun getDate(view: DateTextInputEditText): Calendar {
        return view.date
    }

    @BindingAdapter("app:dateAttrChanged")
    @JvmStatic
    fun setDateListener(view: DateTextInputEditText, attrChange: InverseBindingListener) {
        view.listener = object : DateTextInputEditText.OnDateChangeListener {
            override fun onDateChanged(newValue: Calendar) {
                attrChange.onChange()
            }
        }
    }

    @BindingAdapter("item")
    @JvmStatic
    fun setItem(view: SpinnerTextInputEditText, newValue: SpinnerItem?) {
        if (view.selectedItem != newValue) {
            view.selectedItem = newValue
        }
    }

    @InverseBindingAdapter(attribute = "item")
    @JvmStatic
    fun getItem(view: SpinnerTextInputEditText): SpinnerItem? {
        return view.selectedItem
    }

    @BindingAdapter("app:itemAttrChanged")
    @JvmStatic
    fun setItemListener(view: SpinnerTextInputEditText, attrChange: InverseBindingListener) {
        view.listener = object : SpinnerTextInputEditText.OnItemChangeListener {
            override fun onItemChanged(newItem: SpinnerItem?) {
                attrChange.onChange()
            }
        }
    }

}