package org.codetome.kurator.extensions

import kotlinx.html.*
import org.codetome.kurator.behavior.Includable
import org.codetome.kurator.template.Layout

fun <T> TagConsumer<*>.include(includable: Includable<T>, ctx: T) {
    doInclude(includable, this, ctx)
}

fun TagConsumer<*>.include(includable: Includable<Unit>) {
    doInclude(includable, this)
}

fun <T> FlowContent.include(includable: Includable<T>, ctx: T) {
    doInclude(includable, consumer, ctx)
}

fun FlowContent.include(includable: Includable<Unit>) {
    doInclude(includable, consumer)
}

fun HTML.include(includable: Includable<Unit>) {
    doInclude(includable, consumer)
}

fun <T> HTML.include(includable: Includable<T>, data: T) {
    doInclude(includable, consumer, data)
}

fun HTMLTag.content(content: String) {
    unsafe {
        +content
    }
}

fun <T> HTMLTag.content(layout: Layout<T>, data: T) {
    doInclude(layout, consumer, data)
}

private fun doInclude(includable: Includable<Unit>, consumer: TagConsumer<*>) {
    doInclude(includable, consumer, Unit)
}

private fun <T> doInclude(includable: Includable<T>, consumer: TagConsumer<*>, data: T) {
    includable.templateBuilder.build(consumer, data)
}

