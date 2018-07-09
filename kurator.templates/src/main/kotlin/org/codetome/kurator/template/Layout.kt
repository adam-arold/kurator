package org.codetome.kurator.template

import org.codetome.kurator.behavior.Includable

open class Layout<T>(override val templateBuilder: TemplateBuilder<T>)
    : Includable<T>
