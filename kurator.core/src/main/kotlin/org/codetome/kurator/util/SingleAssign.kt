package org.codetome.kurator.util

import kotlin.reflect.KProperty

// License notice:
//
// The contents of this file are taken from tornadofx: https://github.com/edvin/tornadofx
// It is not written by me but by the author of tornadofx.
// This notice is here to give credit to the author of tornadofx.

fun <T> singleAssign(threadSafetyMode: SingleAssignThreadSafetyMode = SingleAssignThreadSafetyMode.SYNCHRONIZED): SingleAssign<T> =
        if (threadSafetyMode == SingleAssignThreadSafetyMode.SYNCHRONIZED) SynchronizedSingleAssign() else UnsynchronizedSingleAssign()

interface SingleAssign<T> {
    fun isInitialized(): Boolean
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

private class SynchronizedSingleAssign<T> : UnsynchronizedSingleAssign<T>() {

    @Volatile
    override var value: Any? = UninitializedValue

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = synchronized(this) {
        super.setValue(thisRef, property, value)
    }
}

private open class UnsynchronizedSingleAssign<T> : SingleAssign<T> {

    protected object UninitializedValue

    protected open var value: Any? = UninitializedValue

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!isInitialized()) throw UninitializedPropertyAccessException("Value has not been assigned yet!")
        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (isInitialized()) throw Exception("Value has already been assigned!")
        this.value = value
    }

    override fun isInitialized() = value != UninitializedValue
}

enum class SingleAssignThreadSafetyMode {
    SYNCHRONIZED,
    NONE
}
