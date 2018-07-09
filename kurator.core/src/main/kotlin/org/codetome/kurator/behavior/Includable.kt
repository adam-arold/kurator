package org.codetome.kurator.behavior

import kotlinx.html.stream.appendHTML
import org.codetome.kurator.template.TemplateBuilder

/**
 * Represents a html template of some sort which has content and can be
 * included in other templates.
 */
interface Includable<T> {
    val templateBuilder: TemplateBuilder<T>

    fun build(data: T): String {
        val sb = StringBuilder()
        val tagConsumer = sb.appendHTML()
        templateBuilder.build(tagConsumer, data)
        return sb.toString()
    }
}

fun Includable<Unit>.build() = build(Unit)
