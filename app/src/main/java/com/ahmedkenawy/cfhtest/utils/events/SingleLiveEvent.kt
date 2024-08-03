package com.ahmedkenawy.cfhtest.utils.events

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A custom implementation of MutableLiveData that allows for one-time events.
 * It ensures that the event is observed only once by each observer.
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    /**
     * Overrides the observe method to notify the observer only if the event is not pending.
     * This ensures that the observer receives the event only once.
     *
     * @param owner The LifecycleOwner which controls the observer's lifecycle.
     * @param observer The Observer that will be notified of changes.
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        // Observe the internal MutableLiveData
        super.observe(owner, Observer<T> { t ->
            // Notify observer only if the event is not pending
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * Sets the value of the event and marks it as pending.
     * The observer will be notified only once.
     *
     * @param t The value to set.
     */
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     * Sets the value to null to trigger the observer.
     */
    fun call() {
        value = null
    }
}
