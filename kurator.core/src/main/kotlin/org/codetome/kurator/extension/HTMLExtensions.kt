package org.codetome.kurator.extension

import kotlinx.html.*
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.behavior.Includable

fun FlowContent.include(includable: Includable<TemplateContext>, ctx: TemplateContext) {
    doInclude(includable, consumer, ctx)
}

fun HTML.include(includable: Includable<TemplateContext>, ctx: TemplateContext) {
    doInclude(includable, consumer, ctx)
}

fun <T> FlowContent.include(includable: Includable<T>, data: T) {
    doInclude(includable, consumer, data)
}

fun <T> HTML.include(includable: Includable<T>, data: T) {
    doInclude(includable, consumer, data)
}

fun  HTMLTag.content(content: String) {
    unsafe {
        +content
    }
}

private fun <T> doInclude(includable: Includable<T>, consumer: TagConsumer<*>, data: T) {
    includable.contentBuilder.invoke(consumer, data)
}


