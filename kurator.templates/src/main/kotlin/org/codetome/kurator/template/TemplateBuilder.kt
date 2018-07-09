package org.codetome.kurator.template

import kotlinx.html.TagConsumer

class TemplateBuilder<in T>(private val builder: TagConsumer<*>.(T) -> Unit) {

    fun build(tagConsumer: TagConsumer<*>, data: T) {
        builder.invoke(tagConsumer, data)
    }
}
