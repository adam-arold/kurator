package org.codetome.kurator.behavior

import kotlinx.html.TagConsumer

/**
 * Represents a html template of some sort which has content and can be
 * included in other templates.
 */
interface Includable<in T> {
    val contentBuilder: TagConsumer<*>.(T) -> Unit
}
