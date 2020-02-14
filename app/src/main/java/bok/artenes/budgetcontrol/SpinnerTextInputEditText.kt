package bok.artenes.budgetcontrol

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.textfield.TextInputEditText

class SpinnerTextInputEditText : TextInputEditText, View.OnClickListener,
    PopupMenu.OnMenuItemClickListener {

    var items: List<SpinnerItem> = emptyList()
        set(value) {
            field = value
            selectedItem = null
            setText("")
            popupMenu.menu.clear()
            for ((index, item) in value.withIndex()) {
                popupMenu.menu.add(0, index, 0, item.label)
            }
        }

    var selectedItem: SpinnerItem? = null
        set(value) {
            field = items.find { it.uid == value?.uid }
            setText(field?.label)
            listener?.onItemChanged(field)
        }

    var listener: OnItemChangeListener? = null

    private lateinit var popupMenu: PopupMenu

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setOnClickListener(this)
        popupMenu = PopupMenu(context, this)
        popupMenu.setOnMenuItemClickListener(this)
    }

    override fun onClick(v: View?) {
        popupMenu.show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        val index = item.itemId
        selectedItem = items[index]
        setText(selectedItem?.label)
        return true
    }

    interface OnItemChangeListener {
        fun onItemChanged(newItem: SpinnerItem?)
    }

}