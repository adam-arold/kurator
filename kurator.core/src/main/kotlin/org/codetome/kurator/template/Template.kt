package org.codetome.kurator.template

import kotlinx.html.TagConsumer
import org.codetome.kurator.behavior.Includable
import org.codetome.kurator.data.domain.TemplateContext

abstract class Template(override val contentBuilder: TagConsumer<*>.(TemplateContext) -> Unit)
    : Includable<TemplateContext>
