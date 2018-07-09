package org.codetome.kurator.template

import kotlinx.html.TagConsumer
import org.codetome.kurator.behavior.Includable

fun <T> template(builder: TagConsumer<*>.(T) -> Unit): TemplateBuilder<T> {
    return TemplateBuilder(builder)
}

fun <T> templateLayout(builder: TagConsumer<*>.(T) -> Unit): Includable<T> {
    return Layout(TemplateBuilder(builder))
}
