package org.codetome.kurator.template

import kotlinx.html.TagConsumer
import org.codetome.kurator.data.domain.TemplateContext

fun template(builder: TagConsumer<*>.(TemplateContext) -> Unit): TagConsumer<*>.(TemplateContext) -> Unit {
    return builder
}

fun <T> partial(builder: TagConsumer<*>.(T) -> Unit): TagConsumer<*>.(T) -> Unit {
    return builder
}
