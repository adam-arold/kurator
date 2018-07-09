package org.codetome.kurator.behavior

/**
 * Represents an object which can have a name.
 */
interface Nameable {

    /**
     * Returns the name of this object. By default it is the simple name of the object's class.
     */
    fun name(): String = this::class.simpleName!!.toLowerCase()
}
