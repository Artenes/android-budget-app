package bok.artenes.budgetcontrol

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class SingleNotifyLiveData<T> : MutableLiveData<T>() {

    private var version = 0

    override fun setValue(value: T) {
        version++
        super.setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, ObserverWrapper(observer, version))
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(ObserverWrapper(observer, version))
    }

    inner class ObserverWrapper(
        private val observer: Observer<in T>,
        private var lastVersion: Int
    ) : Observer<T> {
        override fun onChanged(t: T) {
            if (lastVersion != version) {
                lastVersion = version
                observer.onChanged(t)
            }
        }
    }

}